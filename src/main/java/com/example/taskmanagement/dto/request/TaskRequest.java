package com.example.taskmanagement.dto.request;

import com.example.taskmanagement.model.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class TaskRequest {
    @NotBlank(message = "Title is required.")
    private String title;

    private String description;

    @NotNull(message = "Deadline is required.")
    @FutureOrPresent(message = "Deadline must be in the future or present.")
    private LocalDateTime deadline;

    @NotNull(message = "Status is required.")
    private TaskStatus status;

    @NotNull(message = "Assigned users are required.")
    private Set<Long> assignedUserIds;



}
