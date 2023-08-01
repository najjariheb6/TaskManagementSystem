package com.najjar.taskmanagementsystem.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.najjar.taskmanagementsystem.model.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    //@FutureOrPresent(message = "Due date must be a future or present date")
    // this annotation make error when patching or updating , until we found a solution ...
    private Timestamp dueDate;
    private Long assignedUserId;

    public Task(long l, String s, String s1) {
    }
}
