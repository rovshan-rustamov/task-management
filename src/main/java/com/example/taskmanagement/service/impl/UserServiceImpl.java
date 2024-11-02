package com.example.taskmanagement.service.impl;

import com.example.taskmanagement.dto.request.CreateUserRequest;
import com.example.taskmanagement.dto.request.PasswordChangeRequest;
import com.example.taskmanagement.dto.response.CreateUserResponse;
import com.example.taskmanagement.dto.response.UserResponse;
import com.example.taskmanagement.exception.BadRequestException;
import com.example.taskmanagement.exception.ErrorCode;
import com.example.taskmanagement.model.GrantedUserAuthority;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.model.UserAuthority;
import com.example.taskmanagement.repository.GrantedUserAuthorityRepository;
import com.example.taskmanagement.repository.OrganizationRepository;
import com.example.taskmanagement.repository.UserRepository;
import com.example.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GrantedUserAuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganizationRepository organizationRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest userRequest) {
        Optional<User> userOptional = userRepository.findByUsername(userRequest.getUsername());
        if (userOptional.isPresent()) {
            throw new BadRequestException(ErrorCode.USER_ALREADY_PRESENT);
        }
        GrantedUserAuthority grantedUserAuthority = authorityRepository.findByUserAuthority(UserAuthority.USER).
                orElseThrow(()-> new RuntimeException("authority not found"));
        String generatedPassword = generateDefaultPassword(); // only admins can create users, so
        // they will be with default password which will be changed on the first login

        User newUser = User.builder()
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(generatedPassword)
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .organization(organizationRepository.findById(userRequest.getOrganizationId())
                        .orElseThrow(()-> new RuntimeException("organization not found")))
                .build();

        return CreateUserResponse.builder()
                .username(userRequest.getUsername())
                .password(generatedPassword)
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return List.of();
    }

    @Override
    public UserResponse getUser(Long id) {
        return null;
    }

    @Override
    public String updateUser(Long id) {
        return "";
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public String changePassword(PasswordChangeRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user1);
            return "success";
        }

        throw new RuntimeException("user not found");
    }

    private String generateDefaultPassword() {
        StringBuilder password = new StringBuilder();
        int passwordLength = 8;

        // the characters that can be used in the password
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            password.append(characters.charAt(randomIndex));
        }

        if (isPasswordValid(password.toString())) {
            return password.toString();
        } else {
            throw new RuntimeException("not valid password generation");
        }


    }

    private boolean isPasswordValid(String password) {
        // Define the regex pattern for at least 6 alphanumeric characters
        String regexPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";

        // Use Pattern and Matcher to validate the password
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
