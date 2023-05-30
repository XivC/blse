package com.itmo.blse.tournaments.processes.tournaments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.dto.CreateTournamentDto;
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
public class ValidateTournamentDataProcess implements JavaDelegate {
    @Autowired
    CreateTournamentValidator createTournamentValidator;
    @Override
    public void execute(DelegateExecution execution) {
        JacksonJsonNode judgesNode = (JacksonJsonNode) execution.getVariable("judges");
        JacksonJsonNode teamsNode = (JacksonJsonNode) execution.getVariable("teams");
        List<Long> judgesIds = new ArrayList<>();
        List<Long> teamsIds = new ArrayList<>();

        for (SpinJsonNode element : judgesNode.elements()) {
            judgesIds.add(Long.valueOf(element.stringValue()));
        }
        for (SpinJsonNode element : teamsNode.elements()) {
            teamsIds.add(Long.valueOf(element.stringValue()));
        }

        CreateTournamentDto data = CreateTournamentDto.builder()
                .name((String)execution.getVariable("tournament_name"))
                .approvalRatio((Double) execution.getVariable("approval_ratio"))
                .maxGames((Integer) execution.getVariable("max_games"))
                .startDate(new Date())
                .judgesIds(judgesIds)
                .teamsIds(teamsIds).build();
        try {
            createTournamentValidator.clean(data);
        }
        catch (ValidationError err){
            execution.setVariable("result", "error");
            return;
        }

        execution.setVariable("result", "success");
        execution.setVariable("validated_data", data);

    }
}
