package com.najjar.taskmanagementsystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.najjar.taskmanagementsystem.model.Task;
import com.najjar.taskmanagementsystem.repositories.TaskRepository;
import com.najjar.taskmanagementsystem.services.TaskService;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void testCreateTask() {
        // Arrange
        Task taskToCreate = new Task();
        taskToCreate.setTitle("Sample Task");
        taskToCreate.setDescription("Sample description");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Sample Task");
        savedTask.setDescription("Sample description");

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        // Act
        Task createdTask = taskService.createTask(taskToCreate);

        // Assert
        assertNotNull(createdTask.getId());
        assertEquals("Sample Task", createdTask.getTitle());
        assertEquals("Sample description", createdTask.getDescription());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

//    @Test
//    public void testGetTaskById() {
//        // Arrange
//        Long taskId = 1L;
//        Task task = new Task();
//        task.setId(taskId);
//        task.setTitle("Sample Task");
//        task.setDescription("Sample description");
//
//        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
//
//        // Act
//        Task foundTask = taskService.getTaskRefById(taskId);
//
//
//        // Assert
//        assertNotNull(foundTask);
//        assertEquals(taskId, foundTask.getId());
//        assertEquals("Sample Task", foundTask.getTitle());
//        assertEquals("Sample description", foundTask.getDescription());
//        verify(taskRepository, times(1)).findById(taskId);
//    }

    @Test
    @Order(2)
    public void testGetAllTasks() {
        // Arrange
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", "Description 1"));
        tasks.add(new Task(2L, "Task 2", "Description 2"));

        when(taskRepository.findAll()).thenReturn(tasks);

        // Act
        List<Task> allTasks = taskService.getAllTasks();

        // Assert
        assertNotNull(allTasks);
        assertEquals(2, allTasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    // Add more tests for other methods in the TaskService class as needed
}

