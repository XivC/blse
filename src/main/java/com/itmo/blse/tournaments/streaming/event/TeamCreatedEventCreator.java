package com.itmo.blse.tournaments.streaming.event;

import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.streaming.converter.TeamCreatedConverter;
import com.itmo.blse.tournaments.streaming.model.TeamCreatedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamCreatedEventCreator {


    @Autowired
    TeamCreatedConverter converter;

    public Event<TeamCreatedModel> createEvent(Team team) {
        TeamCreatedModel data = converter.toEventData(team);
        return new Event<>(data, "team-created");
    }
}
