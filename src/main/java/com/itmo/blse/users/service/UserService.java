package com.itmo.blse.users.service;


import com.itmo.blse.tournaments.model.Roles;
import com.itmo.blse.users.exception.UserNotInContextException;
import com.itmo.blse.users.model.User;
import com.itmo.blse.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserRepository userRepository;

    public User fromContext() throws UserNotInContextException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return userRepository.getUserByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
        }

        throw new UserNotInContextException();
    }

    public List<User> listByRole(Roles role){
        return userRepository.getAllByRolesContains(role);
    }

}
