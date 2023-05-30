package com.itmo.blse.tournaments.processes.teams;

import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.processes.AuthJavaDelegate;
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
public class TeamCreationProcess extends AuthJavaDelegate {
    private final TeamCreator teamCreator;

    @Autowired
    public TeamCreationProcess(TeamCreator teamCreator) {
        this.teamCreator = teamCreator;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
        String teamName = (String) execution.getVariable("team_name");
        execution.setVariable("result", "success");
        execution.setVariable("teamName", teamName + new Date().getTime());
        teamCreator.create(teamName);
    }
}
