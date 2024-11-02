package com.example.taskmanagement.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordChangeRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
