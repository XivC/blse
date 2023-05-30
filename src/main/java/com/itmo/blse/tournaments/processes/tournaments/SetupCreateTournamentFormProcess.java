package com.itmo.blse.tournaments.processes.tournaments;

import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.model.Roles;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.processes.AuthJavaDelegate;
import com.itmo.blse.tournaments.repository.TeamRepository;
import com.itmo.blse.tournaments.service.TeamCreator;
import com.itmo.blse.tournaments.validator.CreateTeamValidator;
import com.itmo.blse.users.model.User;
import com.itmo.blse.users.service.UserService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SetupCreateTournamentFormProcess extends AuthJavaDelegate {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserService userService;

    private String buildTeamsToAdd(){
        List<Team> teams = teamRepository.findAll();
        List<String> internals = new ArrayList<>();
        for (Team team: teams){
            internals.add(
                    String.format(
                            "{\"label\": \"%s\", \"value\": \"%s\"}",
                            team.getName(), team.getId()
                    )
            );
        }
        return "[" + String.join(",", internals) + "]";
    }

    private String buildJudgesToAdd(){
        List<User> judges = userService.listByRole(Roles.JUDGE);
        List<String> internals = new ArrayList<>();
        for (User user: judges){
            internals.add(
                    String.format(
                            "{\"label\": \"%s\", \"value\": \"%s\"}",
                            user.getUsername(), user.getId()
                    )
            );
        }
        return "[" + String.join(",", internals) + "]";
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
        execution.setVariable("teams_form_input", buildTeamsToAdd());
        execution.setVariable("judges_form_input", buildJudgesToAdd());
    }
}
