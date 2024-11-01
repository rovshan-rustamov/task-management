package com.example.taskmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    int status;
    String title;
    String details;
    @Builder.Default
    Map<String, String> data = new HashMap<>();

}
//TODO reset password logic (enters old password, how to check)