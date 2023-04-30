package com.itmo.blse.tournaments.streaming.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class GameDroppedModel {

    private UUID publicId;

}
