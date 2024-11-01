package com.example.taskmanagement.dto;

import com.example.taskmanagement.validators.PasswordsMatch;
import com.example.taskmanagement.validators.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@PasswordsMatch
public class UserAuthRequest {

    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    @NotEmpty(message = "Repeated password must not be empty")
    private String repeatedPassword;

    private String username;
    private String name;
    private String surname;
    private String organizationName;
    private String address;
    @Pattern(
            regexp = "^\\+994(?:70|77|50|51|55|99)\\d{7}$",
            message = "Phone number must start with +994 followed by one of 77, 70, 50, 51, 55, 99 and then 7 digits")
    private String phoneNumber;


}
