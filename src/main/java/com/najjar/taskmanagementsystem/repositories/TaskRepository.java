package com.najjar.taskmanagementsystem.repositories;

import com.najjar.taskmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
