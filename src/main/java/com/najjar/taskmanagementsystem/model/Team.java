package com.najjar.taskmanagementsystem.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long teamId;
    private String name;
    private String description;
}