package com.itmo.blse.tournaments.streaming.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MatchUpdatedModel extends MatchModel {

    private UUID winnerPublicId;

    @Builder
    public MatchUpdatedModel(
            UUID publicId,
            UUID team1PublicId,
            UUID team2PublicId,
            UUID winnerPublicId
    ){
        super(publicId, team1PublicId, team2PublicId);
        this.winnerPublicId = winnerPublicId;
    }

}
