package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.request.TaskRequest;
import com.example.taskmanagement.dto.response.TaskResponse;
import com.example.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create-task")
    public ResponseEntity<TaskResponse> createTask(TaskRequest taskRequest){
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }


    @GetMapping("/all-users")
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.updateTask(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
