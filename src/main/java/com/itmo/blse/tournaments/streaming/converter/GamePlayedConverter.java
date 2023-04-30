package com.itmo.blse.tournaments.streaming.converter;

import com.itmo.blse.tournaments.model.Game;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.streaming.model.GamePlayedModel;
import com.itmo.blse.tournaments.streaming.model.TeamCreatedModel;
import org.springframework.stereotype.Component;

@Component
public class GamePlayedConverter {


    public GamePlayedModel toEventData(Game game) {
        return GamePlayedModel.builder()
                .publicId(game.getPublicId())
                .winnerPublicId(game.getWinner().getPublicId())
                .build();
    }
}


/*

{

"eventId": "uuid",
"createdAt": "2022-12-13",
"routingKey": "analytics.stats",
"action": "team-created",
"data": {
        "publicId": "uuid",
        "name": "asdasd",
    }

}

 */