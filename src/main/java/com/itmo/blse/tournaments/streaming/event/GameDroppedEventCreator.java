package com.itmo.blse.tournaments.streaming.event;

import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.tournaments.model.Game;
import com.itmo.blse.tournaments.streaming.converter.GameDroppedConverter;
import com.itmo.blse.tournaments.streaming.converter.GamePlayedConverter;
import com.itmo.blse.tournaments.streaming.model.GameDroppedModel;
import com.itmo.blse.tournaments.streaming.model.GamePlayedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDroppedEventCreator {

    @Autowired
    GameDroppedConverter converter;

    public Event<GameDroppedModel> createEvent(Game game) {
        GameDroppedModel data = converter.toEventData(game);
        return new Event<>(data, "game-dropped");
    }
}
