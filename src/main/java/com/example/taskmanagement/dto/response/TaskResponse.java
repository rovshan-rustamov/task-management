package com.example.taskmanagement.dto.response;

import com.example.taskmanagement.model.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class TaskResponse {
    private Long id;

    private String title;

    private String description;

    private LocalDateTime deadline;

    private TaskStatus status;

    private Set<UserResponse> assignedUsers;
}
