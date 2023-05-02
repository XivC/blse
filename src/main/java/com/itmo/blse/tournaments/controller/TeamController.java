package com.itmo.blse.tournaments.controller;

import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.dto.TeamDto;
import com.itmo.blse.tournaments.mapper.TeamMapper;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.service.TeamCreator;
import com.itmo.blse.tournaments.service.TeamReader;
import com.itmo.blse.tournaments.validator.CreateTeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/", produces = "application/json")
public class TeamController {

    @Autowired
    TeamCreator teamCreator;

    @Autowired
    CreateTeamValidator createTeamValidator;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    TeamReader teamReader;

    @GetMapping("user/teams/")
    public List<TeamDto> getTeams() {
        return teamReader.getAll().stream().map(teamMapper::toTeamDto).collect(Collectors.toList());
    }

    @GetMapping("user/teams/{id}/")
    public TeamDto getTeamById(@PathVariable Long id) {
        Team team = teamReader.getById(id);
        if (team == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return teamMapper.toTeamDto(team);

    }


    @PostMapping("moderator/teams/")
    public ResponseEntity<?> createTeam(@NonNull @RequestParam String name){
        try {
            createTeamValidator.clean(name);
            Team team = teamCreator.create(name);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamMapper.toTeamDto(team));

        } catch (ValidationError err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getErrors());
        }
    }
}


