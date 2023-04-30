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

    @Builder
    public MatchCreatedModel(
            UUID publicId,
            UUID team1PublicId,
            UUID team2PublicId
    ){
        super(publicId, team1PublicId, team2PublicId);
    }

}
