package com.itmo.blse.tournaments.streaming.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MatchModel {

    String publicId;
    UUID team1PublicId;
    UUID team2PublicId;


}
