package com.najjar.taskmanagementsystem.repositories;

import com.najjar.taskmanagementsystem.model.User;
import com.najjar.taskmanagementsystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByTeamId(Long teamId);
    Optional<User> findByEmail(String email);
}
