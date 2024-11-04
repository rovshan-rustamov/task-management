package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.LoginRequest;
import com.example.taskmanagement.dto.LoginResponse;
import com.example.taskmanagement.dto.UserAuthRequest;
import com.example.taskmanagement.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> registerUser(@Valid @RequestBody UserAuthRequest userAuthRequest) {
        return ResponseEntity.ok(authService.registerUser(userAuthRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> logIn(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.logIn(loginRequest));
    }
}
