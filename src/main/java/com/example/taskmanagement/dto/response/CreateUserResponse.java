package com.example.taskmanagement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private final String username;
    private final String password;
}
