package com.itmo.blse.tournaments.streaming.event;

import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.tournaments.model.Match;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.streaming.converter.MatchUpdatedConverter;
import com.itmo.blse.tournaments.streaming.converter.TournamentCreatedConverter;
import com.itmo.blse.tournaments.streaming.model.MatchUpdatedModel;
import com.itmo.blse.tournaments.streaming.model.TournamentCreatedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchUpdatedEventCreator {


    @Autowired
    MatchUpdatedConverter converter;

    public Event<MatchUpdatedModel> createEvent(Match match, Team winner) {
        MatchUpdatedModel data = converter.toEventData(match, winner);
        return new Event<>(data, "match-updated");
    }
}
