package com.itmo.blse.tournaments.service;


import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.app.streaming.EventPublisher;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.repository.TeamRepository;
import com.itmo.blse.tournaments.streaming.event.TeamCreatedEventCreator;
import com.itmo.blse.tournaments.streaming.model.TeamCreatedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class TeamCreator {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    EventPublisher publisher;

    @Autowired
    TeamCreatedEventCreator teamCreatedEventCreator;

    @Transactional
    public Team create(String name) {
        Team team = Team.builder().name(name).build();
        teamRepository.save(team);
        Event<TeamCreatedModel> event = teamCreatedEventCreator.createEvent(team);
        publisher.publish(event);
        return team;
    }
}
