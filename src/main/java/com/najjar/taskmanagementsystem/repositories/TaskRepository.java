package com.najjar.taskmanagementsystem.repositories;

import com.najjar.taskmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedUserId(Long userId);
}
