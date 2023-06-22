package com.najjar.taskmanagementsystem.repositories;

import com.najjar.taskmanagementsystem.model.Team;
import com.najjar.taskmanagementsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
