package com.najjar.taskmanagementsystem.controllers;

import com.najjar.taskmanagementsystem.model.Team;
import com.najjar.taskmanagementsystem.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @GetMapping
    @PreAuthorize("hasAuthority('developer:read')")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developer:read')")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('manager:create')")
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('manager:update')")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('manager:delete')")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
