package com.najjar.taskmanagementsystem.controllers;

import com.najjar.taskmanagementsystem.model.Task;
import com.najjar.taskmanagementsystem.model.dto.ApiError;
import com.najjar.taskmanagementsystem.model.dto.ApiResponse;
import com.najjar.taskmanagementsystem.model.enums.TaskStatus;
import com.najjar.taskmanagementsystem.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public Optional<Task> getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
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
    public Task updateTask(@PathVariable Long id, @Validated @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{taskId}/status/{status}")
    public ResponseEntity<Object> updateTaskStatus(@PathVariable Long taskId, @PathVariable String status) {
        try {
            if(!isValidStatus(status))
                return ResponseEntity.badRequest().body("Invalid status provided");
            taskService.updateTaskStatus(taskId, TaskStatus.valueOf(status));
            return ResponseEntity.ok("Task status updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }
    private boolean isValidStatus(String status) {
        // Check if the status exists in the enum
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.name().equals(status)) {
                return true;
            }
        }
        return false;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam(value = "priority", required = false) Integer priority,
                                                  @RequestParam(value = "status", required = false) String status,
                                                  @RequestParam(value = "assignedTo", required = false) Long assignedTo) {

        List<Task> tasks = taskService.searchTasks(priority, status, assignedTo);
        return ResponseEntity.ok(tasks);
    }
}

