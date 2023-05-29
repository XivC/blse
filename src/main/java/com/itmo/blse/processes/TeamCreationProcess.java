package com.itmo.blse.processes;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Date;


@Named
public class TeamCreationProcess implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        String teamName = (String) execution.getVariable("team_name");

        execution.setVariable("team_name", teamName + new Date().getTime());
    }
}
