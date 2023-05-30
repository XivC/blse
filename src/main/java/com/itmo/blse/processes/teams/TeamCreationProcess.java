package com.itmo.blse.processes.teams;

import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.repository.TeamRepository;
import com.itmo.blse.tournaments.service.TeamCreator;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.util.Date;


@Component
public class TeamCreationProcess implements JavaDelegate {
    @Autowired
    TeamCreator teamCreator;
    @Override
    public void execute(DelegateExecution execution) {

        String teamName = (String) execution.getVariable("team_name");
        execution.setVariable("result", "success");
        execution.setVariable("teamName", teamName + new Date().getTime());
        teamCreator.create(teamName);
    }
}
