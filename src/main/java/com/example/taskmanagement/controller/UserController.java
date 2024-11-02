package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.request.CreateUserRequest;
import com.example.taskmanagement.dto.request.PasswordChangeRequest;
import com.example.taskmanagement.dto.response.CreateUserResponse;
import com.example.taskmanagement.dto.response.UserResponse;
import com.example.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest request) {

        return ResponseEntity.ok(userService.changePassword(request));
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
