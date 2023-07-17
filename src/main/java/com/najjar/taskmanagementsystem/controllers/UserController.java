package com.najjar.taskmanagementsystem.controllers;

import com.najjar.taskmanagementsystem.model.User;
import com.najjar.taskmanagementsystem.model.dto.ApiError;
import com.najjar.taskmanagementsystem.model.dto.ApiResponse;
import com.najjar.taskmanagementsystem.model.dto.UserDTO;
import com.najjar.taskmanagementsystem.model.dto.UserMapper;
import com.najjar.taskmanagementsystem.model.enums.Roles;
import com.najjar.taskmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('manager:read')")
    public List<UserDTO> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .teamId(user.getTeamId())
                        .build())
                .collect(Collectors.toList());
        return userDTOList;
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .teamId(user.getTeamId())
                .build();
        return userDTO;
    }

    @GetMapping("/team/{teamId}")
    @PreAuthorize("hasAuthority('manager:read')")
    public List<User> getUsersByTeamId(@PathVariable Long teamId) {
        return userService.getUsersByTeamId(teamId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('manager:create')")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('developer:update')")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        User currentUser = getCurrentUser();
        if (currentUser != null && currentUser.getId().equals(id)) {
            if (updatedUserDTO.getName() != null)
                currentUser.setName(updatedUserDTO.getName());
            if (updatedUserDTO.getEmail() != null)
                currentUser.setEmail(updatedUserDTO.getEmail());
            User savedUser = userService.updateUser(id, currentUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<>("success", "Updated successfully", userMapper.convertToDto(savedUser))
            );
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(
                                "Error",
                                "FORBIDDEN: You do not have permission to perform this action.",
                                null
                        )
                );
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasAuthority('manager:update')") //to be modified
    public ResponseEntity<ApiResponse> updateUserRole(@PathVariable Long id, @RequestBody Roles role) {
        Optional<User> existingUserOptional = userService.getUserById(id);
        if (existingUserOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Error", "NOT_FOUND: User not found with ID: " + id, null));
        }

        User existingUser = existingUserOptional.get();
        existingUser.setRole(role);
        User updatedUser = userService.updateUser(id, existingUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("success", "Updated successfully", userMapper.convertToDto(updatedUser))
        );
    }

    @PutMapping("/{id}/team")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ApiResponse> updateUserTeam(@PathVariable Long id, @RequestBody Long teamId) {
        Optional<User> existingUserOptional = userService.getUserById(id);
        if (existingUserOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Error", "NOT_FOUND: User not found with ID: " + id, null));
        }

        User existingUser = existingUserOptional.get();
        existingUser.setTeamId(teamId);
        User updatedUser = userService.updateUser(id, existingUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("success", "Updated successfully", userMapper.convertToDto(updatedUser))
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    @GetMapping("/current_user")
    public ResponseEntity<UserDTO> getCurrentUserDetails() {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            UserDTO userDTO = userMapper.convertToDto(currentUser);
            return ResponseEntity.ok(userDTO);
        }
        // Return appropriate response if no authenticated user found
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
