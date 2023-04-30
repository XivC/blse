package com.itmo.blse.tournaments.streaming.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class GamePlayedModel {

    private UUID publicId;
    private UUID matchPublicId;
    private UUID winnerPublicId;


}
