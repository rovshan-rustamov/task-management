package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.request.TaskRequest;
import com.example.taskmanagement.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTask();

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse getTask(Long id);

    String updateTask(Long id);

    void deleteUser(Long id);
}
