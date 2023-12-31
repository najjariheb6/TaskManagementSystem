package com.najjar.taskmanagementsystem.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long teamId;
    private String name;
    private String description;
}