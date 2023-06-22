package com.najjar.taskmanagementsystem.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int status;
    private int priority;
    private LocalDate dueDate;
    private Long assignedUserId;

//    public void assignToUser(User user) {
//        this.assignedUser = user;
//    }
//
//    public void changeStatus(int status) {
//        this.status = status;
//    }
//
//    public void updateTask(String title, String description, int priority, LocalDate dueDate) {
//        this.title = title;
//        this.description = description;
//        this.priority = priority;
//        this.dueDate = dueDate;
//    }
}
