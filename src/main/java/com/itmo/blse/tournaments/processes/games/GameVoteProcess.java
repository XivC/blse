package com.itmo.blse.processes.games;

import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.processes.AuthJavaDelegate;
import com.itmo.blse.tournaments.service.GameService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;


@Named
public class GameVoteProcess extends AuthJavaDelegate {

    @Autowired
    GameService gameService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        super.execute(delegateExecution);
        long gameId =  Long.parseLong((String) delegateExecution.getVariable("game_id"));
        boolean isApproved = (boolean) delegateExecution.getVariable("is_approved");
        gameService.approveGame(gameId, isApproved);
    }
}
