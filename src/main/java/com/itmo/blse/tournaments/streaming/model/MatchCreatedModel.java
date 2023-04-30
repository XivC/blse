package com.itmo.blse.tournaments.streaming.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MatchCreatedModel extends MatchModel {
    String tournamentPublicId;

    @Builder
    public MatchCreatedModel(
            String publicId,
            String tournamentPublicId,
            UUID team1PublicId,
            UUID team2PublicId
    ){
        super(publicId, team1PublicId, team2PublicId);
        this.tournamentPublicId = tournamentPublicId;
    }

}
