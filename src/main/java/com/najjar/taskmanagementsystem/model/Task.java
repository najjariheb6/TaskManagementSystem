package com.najjar.taskmanagementsystem.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

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
    private Timestamp dueDate;
    private Long assignedUserId;

//    public void assignToUser(User user) {
//        this.assignedUser = user;
//    }
//
//    public void changeStatus(int status) {
//        this.status = status;
//    }
//

}
