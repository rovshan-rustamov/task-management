package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.request.CreateUserRequest;
import com.example.taskmanagement.dto.request.PasswordChangeRequest;
import com.example.taskmanagement.dto.response.CreateUserResponse;
import com.example.taskmanagement.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long id);

    String updateUser(Long id);

    void deleteUser(Long id);

    String changePassword(PasswordChangeRequest request);
}
