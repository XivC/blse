package com.itmo.blse.tournaments.processes.matches;

import com.itmo.blse.tournaments.processes.AuthJavaDelegate;
import com.itmo.blse.tournaments.service.GameService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;


@Named
public class MatchPlayProcess extends AuthJavaDelegate {

    @Autowired
    GameService gameService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        super.execute(delegateExecution);
        long matchId = Long.parseLong((String) delegateExecution.getVariable("match_id"));
        long winnerId = Long.parseLong((String) delegateExecution.getVariable("winner_id"));
        gameService.playGame(matchId, winnerId);
    }
}
