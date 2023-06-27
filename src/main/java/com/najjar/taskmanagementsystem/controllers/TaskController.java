package com.najjar.taskmanagementsystem.controllers;

import com.najjar.taskmanagementsystem.model.Task;
import com.najjar.taskmanagementsystem.model.dto.ApiError;
import com.najjar.taskmanagementsystem.model.dto.ApiResponse;
import com.najjar.taskmanagementsystem.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@Validated @RequestBody Task task, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            ApiResponse<Task> response = new ApiResponse<>("error", "An error occurred", null, new ApiError(400, "Validation Error", errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Task createdTask = taskService.createTask(task);

        ApiResponse<Task> response = new ApiResponse<>("success", "Task created successfully", createdTask, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,@Validated @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTaskByUserId(@PathVariable Long userId){
        return taskService.getTasksByUserId(userId);
    }
}

