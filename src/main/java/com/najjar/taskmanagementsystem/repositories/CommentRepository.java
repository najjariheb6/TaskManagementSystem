package com.najjar.taskmanagementsystem.repositories;

import com.najjar.taskmanagementsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTaskId(Long taskId);

    List<Comment> findByUserId(Long userId);
}
