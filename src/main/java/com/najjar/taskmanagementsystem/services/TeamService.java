package com.najjar.taskmanagementsystem.services;

import com.najjar.taskmanagementsystem.model.Team;
import com.najjar.taskmanagementsystem.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Team not found with ID: " + id));
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team updatedTeam) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Team not found with ID: " + id));

        existingTeam.setName(updatedTeam.getName());
        existingTeam.setDescription(updatedTeam.getDescription());

        return teamRepository.save(existingTeam);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
