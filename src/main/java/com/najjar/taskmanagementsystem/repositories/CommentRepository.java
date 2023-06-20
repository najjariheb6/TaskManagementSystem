package com.najjar.taskmanagementsystem.repositories;

import com.najjar.taskmanagementsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
