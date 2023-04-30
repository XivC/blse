package com.itmo.blse.tournaments.streaming.converter;

import com.itmo.blse.tournaments.model.Game;
import com.itmo.blse.tournaments.streaming.model.GameDroppedModel;
import com.itmo.blse.tournaments.streaming.model.GamePlayedModel;
import org.springframework.stereotype.Component;

@Component
public class GameDroppedConverter {


    public GameDroppedModel toEventData(Game game) {
        return GameDroppedModel.builder()
                .publicId(game.getPublicId())
                .build();
    }
}

