package com.itmo.blse.processes.matches;

import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.model.Game;
import com.itmo.blse.tournaments.service.GameService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;


@Named
public class MatchPlayProcess implements JavaDelegate {

    @Autowired
    GameService gameService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws ValidationError {
        long matchId = (long) delegateExecution.getVariable("match_id");
        long winnerId = (long) delegateExecution.getVariable("winner_id");
        gameService.playGame(matchId, winnerId);
    }
}
