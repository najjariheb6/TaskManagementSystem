package com.najjar.taskmanagementsystem.model.dto;

import com.najjar.taskmanagementsystem.model.enums.Roles;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Roles role;
    private Long teamId;
}
