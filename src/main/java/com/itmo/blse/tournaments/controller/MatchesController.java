package com.itmo.blse.tournaments.controller;

import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.mapper.GameMapper;
import com.itmo.blse.tournaments.mapper.MatchMapper;
import com.itmo.blse.tournaments.mapper.TournamentMapper;
import com.itmo.blse.tournaments.model.Game;
import com.itmo.blse.tournaments.model.Match;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/moderator/matches", produces = "application/json")
public class MatchesController {

    @Autowired
    GameService gameService;

    @Autowired
    MatchMapper matchMapper;

    @Autowired
    GameMapper gameMapper;

    @Autowired
    TournamentMapper tournamentMapper;

    @PostMapping("/{id}/play-game/")
    public ResponseEntity<?> playGame(@PathVariable Long id, @RequestParam Long winnerId) {
        try {
            Game game = gameService.playGame(id, winnerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(gameMapper.toGameDto(game));
        } catch (ValidationError err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getErrors());
        }
    }

    @PostMapping("/{id}/drop/")
    public ResponseEntity<?> drop(@PathVariable Long id) {
        try {
            Tournament tournament = gameService.dropMatch(id);
            return ResponseEntity.status(HttpStatus.OK).body(tournamentMapper.toRetrieveTournamentDto(tournament));
        } catch (ValidationError err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getErrors());
        }
    }
}


