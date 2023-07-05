package com.najjar.taskmanagementsystem.services;

import com.najjar.taskmanagementsystem.model.Task;
import com.najjar.taskmanagementsystem.model.User;
import com.najjar.taskmanagementsystem.model.enums.TaskStatus;
import com.najjar.taskmanagementsystem.repositories.TaskRepository;
import com.najjar.taskmanagementsystem.repositories.UserRepository;
import com.najjar.taskmanagementsystem.services.criteria.TaskSpecifications;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public List<Task> searchTasks(Integer priority, String status, Long assignedTo) {
        // Create a specification for filtering tasks based on the provided criteria
        Specification<Task> spec = Specification.where(null);

        // Add filters based on the provided parameters
        if (status != null && EnumUtils.isValidEnum(TaskStatus.class, status)) {
            spec = spec.and(TaskSpecifications.hasStatus(status));
        }
        if (assignedTo != null) {
            spec = spec.and(TaskSpecifications.isAssignedTo(assignedTo));
        }
        if (priority != null) {
            spec = spec.and(TaskSpecifications.hasPriority(priority));
        }

        // Perform the search using the specification
        List<Task> tasks = taskRepository.findAll(spec);

        return tasks;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + id));
        Long assignedUserId = updatedTask.getAssignedUserId();
        User user = userRepository.findById(assignedUserId).orElseThrow(() -> new NoSuchElementException("User not found"));

        // Update the fields of the existing task with the new values
        //if (updatedTask.getDescription() != null) {...}
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setAssignedUserId(assignedUserId);

        // Save the updated task in the database
        return taskRepository.save(existingTask);
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task existingTask = taskRepository.getReferenceById(id);

        existingTask.setStatus(TaskStatus.valueOf(status.toString()));

        // Save the updated task in the database
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
