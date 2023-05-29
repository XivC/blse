package com.itmo.blse.processes;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;


@Named
public class UnFinalProcess implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        String teamName = (String) execution.getVariable("team_name");

        // Perform the necessary operations to create a team
        // For simplicity, let's just print the team details
        System.out.println("Creating team with name: " + teamName);

        // You can perform additional logic here, such as calling a service or persisting the team to a database

        // Set any necessary variables for the next steps in the process
        // For example, you might set a team ID variable if needed
    }
}
