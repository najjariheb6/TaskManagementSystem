package com.najjar.taskmanagementsystem.controllers;

import com.najjar.taskmanagementsystem.model.User;
import com.najjar.taskmanagementsystem.model.dto.UserDTO;
import com.najjar.taskmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
//@PreAuthorize("hasRole('Admin')")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
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

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/team/{teamId}")
    public List<User> getUsersByTeamId(@PathVariable Long teamId) {
        return userService.getUsersByTeamId(teamId);
    }
}
