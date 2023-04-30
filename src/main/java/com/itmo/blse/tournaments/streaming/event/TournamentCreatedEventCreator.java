package com.itmo.blse.tournaments.streaming.event;

import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.streaming.converter.TeamCreatedConverter;
import com.itmo.blse.tournaments.streaming.converter.TournamentCreatedConverter;
import com.itmo.blse.tournaments.streaming.model.TeamCreatedModel;
import com.itmo.blse.tournaments.streaming.model.TournamentCreatedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentCreatedEventCreator {


    @Autowired
    TournamentCreatedConverter converter;

    public Event<TournamentCreatedModel> createEvent(Tournament tournament) {
        TournamentCreatedModel data = converter.toEventData(tournament);
        return new Event<>(data, "tournament-created");
    }
}
