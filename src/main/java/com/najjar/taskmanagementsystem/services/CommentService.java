package com.najjar.taskmanagementsystem.services;

import com.najjar.taskmanagementsystem.model.Comment;

import com.najjar.taskmanagementsystem.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with ID: " + id));
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with ID: " + id));

        existingComment.setTaskId(updatedComment.getTaskId());
        existingComment.setUserId(updatedComment.getUserId());
        existingComment.setMessage(updatedComment.getMessage());
        existingComment.setTimestamp(updatedComment.getTimestamp());

        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
