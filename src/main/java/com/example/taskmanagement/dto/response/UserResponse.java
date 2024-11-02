package com.example.taskmanagement.dto.response;

import com.example.taskmanagement.model.Task;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String organization;
    private List<Task> tasks = new LinkedList<>();
}
