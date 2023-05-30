package com.itmo.blse.users.service;


import com.itmo.blse.a12n.dto.UserRegistrationRequest;
import com.itmo.blse.a12n.service.UserAccountService;
import com.itmo.blse.a12n.service.UserRegistrationService;
import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.tournaments.model.Roles;
import com.itmo.blse.users.exception.UserNotInContextException;
import com.itmo.blse.users.model.User;
import com.itmo.blse.users.repository.UserRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    UserAccountService userAccountService;

    public void camundaAuth(DelegateExecution delegateExecution) {
        IdentityService identityService = delegateExecution.getProcessEngineServices().getIdentityService();
        String username = identityService.getCurrentAuthentication().getUserId();
        UserDetails userDetails = userAccountService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User fromContext() throws UserNotInContextException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            return userRepository.getUserByUsername(username);
        }
        throw new UserNotInContextException();
    }

    public List<User> listByRole(Roles role) {
        return userRepository.getAllByRolesContains(role);
    }

}
