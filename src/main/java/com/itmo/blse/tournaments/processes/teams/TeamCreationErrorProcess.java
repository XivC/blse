package com.itmo.blse.processes.teams;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class TeamCreationErrorProcess implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        throw new BpmnError("teamCreationError",
                String.format("Can't create team with name %s", execution.getVariable("teamName"))
        );
    }

}
