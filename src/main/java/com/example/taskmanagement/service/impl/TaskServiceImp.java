package com.example.taskmanagement.service.impl;

import com.example.taskmanagement.dto.request.TaskRequest;
import com.example.taskmanagement.dto.response.TaskResponse;
import com.example.taskmanagement.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {
    @Override
    public List<TaskResponse> getAllTask() {
        return List.of();
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        return null;
    }

    @Override
    public TaskResponse getTask(Long id) {
        return null;
    }

    @Override
    public String updateTask(Long id) {
        return "";
    }

    @Override
    public void deleteUser(Long id) {

    }
}
