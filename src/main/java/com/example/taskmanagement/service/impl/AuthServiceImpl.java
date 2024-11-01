package com.example.taskmanagement.service.impl;

import com.example.taskmanagement.dto.LoginResponse;
import com.example.taskmanagement.dto.UserAuthRequest;
import com.example.taskmanagement.exception.BadRequestException;
import com.example.taskmanagement.exception.ErrorCode;
import com.example.taskmanagement.model.*;
import com.example.taskmanagement.repository.*;
import com.example.taskmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.taskmanagement.model.UserAuthority.ORGANIZATION_ADMIN;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final GrantedUserAuthorityRepository grantedUserAuthorityRepository;
    private final OrganizationRepository organizationRepository;
    private final TaskRepository taskRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public LoginResponse registerUser(UserAuthRequest userAuthRequest) {
        Optional<User> userOptional = userRepository.findByUsername(userAuthRequest.getUsername());
        if (userOptional.isPresent()) {
            throw new BadRequestException(ErrorCode.USER_ALREADY_PRESENT, userAuthRequest.getUsername());
        }
        GrantedUserAuthority grantedUserAuthority = grantedUserAuthorityRepository.findByUserAuthority(ORGANIZATION_ADMIN)
                .orElseThrow(() -> new RuntimeException("authority not found"));
        Address address = Address.builder()
                .address(userAuthRequest.getAddress())
                .build();
        addressRepository.save(address);
        Organization organization = Organization.builder()
                .organizationName(userAuthRequest.getOrganizationName())
                .address(address)
                .build();
        organizationRepository.save(organization);
        User user = User.builder()
                .name(userAuthRequest.getName())
                .surname(userAuthRequest.getSurname())
                .username(userAuthRequest.getUsername())
                .email(userAuthRequest.getEmail())
                .organization(organization)
                .password(passwordEncoder.encode(userAuthRequest.getPassword()))
                .authorities(List.of(grantedUserAuthority))
                .build();
        userRepository.save(user);
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneNumber(userAuthRequest.getPhoneNumber())
                .user(user)
                .build();
        phoneNumberRepository.save(phoneNumber);

        return LoginResponse.builder()
                .token("sometimea")
                .build();



    }

    /*
      GrantedUserAuthority grantedUserAuthority = grantedUserAuthorityRepository.findById(1L).get();
        Address address = Address.builder()
                .address("NZS")
                .build();
        addressRepository.save(address);
        Organization organization = Organization.builder()
                .organizationName("ROVSHAN CORPORATION")
                .address(address)
                .build();
        organizationRepository.save(organization);
        User user = User.builder()
                .name("Rovshan")
                .surname("Rustamov")
                .username("rovshan123")
                .email("rustamov@gmail.com")
                .password(passwordEncoder.encode("123456789"))
                .authorities(List.of(grantedUserAuthority))
                .build();
        userRepository.save(user);
        PhoneNumber phoneNumber = PhoneNumber.builder()
                .phoneNumber("+994-55-479-43-11")
                .user(user)
                .build();
        phoneNumberRepository.save(phoneNumber);
        Task task = Task.builder()
                .assignedUsers(Set.of(user))
                .deadline(LocalDateTime.now())
                .description("some sort of description")
                .title("Amazing title")
                .organization(organization)
                .status(TaskStatus.New)
                .build();
        taskRepository.save(task);
     */






}
