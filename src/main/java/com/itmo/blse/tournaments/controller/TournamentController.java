package com.itmo.blse.tournaments.controller;

import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.app.streaming.EventPublisher;
import com.itmo.blse.tournaments.dto.CreateTournamentDto;
import com.itmo.blse.tournaments.dto.ListTournamentDto;
import com.itmo.blse.tournaments.dto.RetrieveTournamentDto;
import com.itmo.blse.tournaments.mapper.TournamentMapper;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.service.MatchesTreeBuilder;
import com.itmo.blse.tournaments.service.TournamentCreator;
import com.itmo.blse.tournaments.service.TournamentReader;
import com.itmo.blse.tournaments.streaming.event.TournamentCreatedEventCreator;
import com.itmo.blse.tournaments.streaming.model.TournamentCreatedModel;
import com.itmo.blse.tournaments.validator.CreateTournamentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/", produces = "application/json")
public class TournamentController {

    @Autowired
    TournamentReader tournamentReader;

    @Autowired
    CreateTournamentValidator createTournamentValidator;

    @Autowired
    TournamentCreator tournamentCreator;

    @Autowired
    TournamentMapper tournamentMapper;

    @Autowired
    MatchesTreeBuilder matchesTreeBuilder;

    @Autowired
    EventPublisher eventPublisher;

    @Autowired
    TournamentCreatedEventCreator tournamentCreatedEventCreator;

    @GetMapping("/user/tournaments/")
    public List<ListTournamentDto> getTournaments() {
        return tournamentReader
                .getAll()
                .stream()
                .map(tournamentMapper::toListTournamentDto)
                .collect(Collectors.toList());
    }

    @GetMapping("user/tournaments/{id}/")
    public RetrieveTournamentDto getTournamentById(@PathVariable Long id) {
        Tournament tournament = tournamentReader.getById(id);
        if (tournament == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return tournamentMapper.toRetrieveTournamentDto(tournament);

    }


    @PostMapping("/moderator/tournaments/")
    public ResponseEntity<?> createTournament(@RequestBody CreateTournamentDto createTournamentDto){
        try {
            createTournamentValidator.clean(createTournamentDto);
            Tournament tournament = tournamentCreator.create(createTournamentDto);
            matchesTreeBuilder.buildMatchesTree(tournament);
            Event<TournamentCreatedModel> event = tournamentCreatedEventCreator.createEvent(tournament);
            eventPublisher.publish(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(tournamentMapper.toRetrieveTournamentDto(tournament));

        } catch (ValidationError err){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getErrors());
        }
    }
}


