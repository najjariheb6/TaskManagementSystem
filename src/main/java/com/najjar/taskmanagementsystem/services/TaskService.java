package com.najjar.taskmanagementsystem.services;

import com.najjar.taskmanagementsystem.model.Task;
import com.najjar.taskmanagementsystem.model.User;
import com.najjar.taskmanagementsystem.repositories.TaskRepository;
import com.najjar.taskmanagementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id) {
        return taskRepository.getReferenceById(id);
    }

    public List<Task> getTasksByUserId(Long userId){
        return taskRepository.findByAssignedUserId(userId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + id));
        Long assignedUserId = updatedTask.getAssignedUserId();
        User user = userRepository.findById(assignedUserId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

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

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
