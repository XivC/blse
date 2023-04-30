package com.itmo.blse.tournaments.streaming.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TournamentCreatedModel {

    private String publicId;
    private String name;
    private Date startedAt;
    private List<UUID> teams;
    private List<MatchCreatedModel> matches;


}
