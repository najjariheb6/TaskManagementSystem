package com.najjar.taskmanagementsystem.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private Long id;
    private String title;
    private String description;
    private int status;
    private int priority;
    private Date dueDate;
    @ManyToOne
    private User assignedUser;
}
