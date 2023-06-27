package com.najjar.taskmanagementsystem.model;
import com.najjar.taskmanagementsystem.model.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
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
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Min(value=1, message="Priority must be between 1:LOW, 2:MEDIUM, 3:HIGH")
    @Max(value=3, message="Priority must be between 1:LOW, 2:MEDIUM, 3:HIGH")
    private int priority;
    @FutureOrPresent(message = "Due date must be a future or present date")
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
