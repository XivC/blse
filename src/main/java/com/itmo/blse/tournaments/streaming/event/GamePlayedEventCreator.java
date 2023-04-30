package com.itmo.blse.tournaments.streaming.event;

import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.tournaments.model.Game;
import com.itmo.blse.tournaments.model.Match;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.streaming.converter.GamePlayedConverter;
import com.itmo.blse.tournaments.streaming.converter.MatchUpdatedConverter;
import com.itmo.blse.tournaments.streaming.model.GamePlayedModel;
import com.itmo.blse.tournaments.streaming.model.MatchUpdatedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePlayedEventCreator {


    @Autowired
    GamePlayedConverter converter;

    public Event<GamePlayedModel> createEvent(Game game) {
        GamePlayedModel data = converter.toEventData(game);
        return new Event<>(data, "game-played");
    }
}
