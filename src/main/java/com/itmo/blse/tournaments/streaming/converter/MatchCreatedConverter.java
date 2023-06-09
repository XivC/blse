package com.itmo.blse.tournaments.streaming.converter;

import com.itmo.blse.tournaments.model.Match;
import com.itmo.blse.tournaments.streaming.model.MatchCreatedModel;
import org.springframework.stereotype.Component;

@Component
public class MatchCreatedConverter{


    public MatchCreatedModel toEventData(Match match) {
        MatchCreatedModel.MatchCreatedModelBuilder builder = MatchCreatedModel.builder()
                .publicId(match.getPublicId());

        if (match.getTeam1() != null)
            builder.team1PublicId(match.getTeam1().getPublicId());
        if (match.getTeam2() != null)
            builder.team2PublicId(match.getTeam2().getPublicId());

        return builder.build();

    }
}
