package com.itmo.blse.tournaments.streaming.converter;

import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.streaming.model.TeamCreatedModel;
import org.springframework.stereotype.Component;

@Component
public class TeamCreatedConverter {


    public TeamCreatedModel toEventData(Team team) {
        return TeamCreatedModel.builder()
                .publicId(team.getPublicId())
                .name(team.getName())
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