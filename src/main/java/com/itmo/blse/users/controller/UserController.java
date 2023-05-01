package com.itmo.blse.users.controller;

import com.itmo.blse.a12n.service.UserRegistrationService;
import com.itmo.blse.tournaments.model.Roles;
import com.itmo.blse.users.dto.UserDto;
import com.itmo.blse.users.exception.UserNotInContextException;
import com.itmo.blse.users.mapper.UserMapper;
import com.itmo.blse.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/me/")
    public ResponseEntity<?> me(){
        try {
            return ResponseEntity.ok(userMapper.toUserDto(userService.fromContext()));
        }
        catch (UserNotInContextException ex){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/judges/")
    public List<UserDto> getJudges() {
        return userService.listByRole(Roles.JUDGE).stream().map(userMapper::toUserDto).collect(Collectors.toList());

    }



}
