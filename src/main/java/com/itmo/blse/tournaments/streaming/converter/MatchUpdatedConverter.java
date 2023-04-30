package com.itmo.blse.tournaments.streaming.converter;

import com.itmo.blse.tournaments.model.Match;
import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.streaming.model.MatchCreatedModel;
import com.itmo.blse.tournaments.streaming.model.MatchUpdatedModel;
import org.springframework.stereotype.Component;

@Component
public class MatchUpdatedConverter {

    public MatchUpdatedModel toEventData(Match match, Team winner) {
        MatchUpdatedModel.MatchUpdatedModelBuilder builder = MatchUpdatedModel.builder()
                .publicId(match.getPublicId());

        if (match.getTeam1() != null)
            builder.team1PublicId(match.getTeam1().getPublicId());
        if (match.getTeam2() != null)
            builder.team2PublicId(match.getTeam2().getPublicId());

        if (winner != null)
            builder.winnerPublicId(winner.getPublicId());

        return builder.build();

    }
}
