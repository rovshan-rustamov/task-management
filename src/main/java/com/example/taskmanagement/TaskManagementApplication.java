package com.example.taskmanagement;

import com.example.taskmanagement.model.*;
import com.example.taskmanagement.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class TaskManagementApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final GrantedUserAuthorityRepository grantedUserAuthorityRepository;
    private final OrganizationRepository organizationRepository;
    private final TaskRepository taskRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {


    }
}
