package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.LoginResponse;
import com.example.taskmanagement.dto.UserAuthRequest;

public interface AuthService {
    LoginResponse registerUser(UserAuthRequest userAuthRequest);
}