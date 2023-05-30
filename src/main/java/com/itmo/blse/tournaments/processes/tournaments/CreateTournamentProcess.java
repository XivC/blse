package com.itmo.blse.tournaments.processes.tournaments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.dto.CreateTournamentDto;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.service.TournamentCreator;
import com.itmo.blse.tournaments.validator.CreateTournamentValidator;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.impl.json.jackson.JacksonJsonNode;
import org.camunda.spin.json.SpinJsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class CreateTournamentProcess implements JavaDelegate {
    @Autowired
    TournamentCreator tournamentCreator;

    @Autowired
    CreateTournamentValidator createTournamentValidator;
    @Override
    public void execute(DelegateExecution execution) {

        CreateTournamentDto data = (CreateTournamentDto) execution.getVariable("validated_data");
        try {
            Tournament tournament = tournamentCreator.create(data);
            execution.setVariable("result", "success");
            execution.setVariable("tournament", tournament);
        }
        catch (ValidationError err){
            execution.setVariable("result", "error");
        }


    }
}
