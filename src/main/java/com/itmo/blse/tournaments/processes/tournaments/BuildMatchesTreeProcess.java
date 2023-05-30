package com.itmo.blse.tournaments.processes.tournaments;

import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.dto.CreateTournamentDto;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.processes.AuthJavaDelegate;
import com.itmo.blse.tournaments.service.MatchesTreeBuilder;
import com.itmo.blse.tournaments.service.TournamentCreator;
import com.itmo.blse.tournaments.validator.CreateTournamentValidator;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BuildMatchesTreeProcess extends AuthJavaDelegate {

    @Autowired
    MatchesTreeBuilder matchesTreeBuilder;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
        Tournament tournament = (Tournament) execution.getVariable("tournament");
        matchesTreeBuilder.buildMatchesTree(tournament);
        execution.setVariable("result", "success");

    }
}
