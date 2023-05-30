package com.itmo.blse.tournaments.processes;

import com.itmo.blse.users.service.UserService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthJavaDelegate implements JavaDelegate {

    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        userService.camundaAuth(delegateExecution);
    }
}
