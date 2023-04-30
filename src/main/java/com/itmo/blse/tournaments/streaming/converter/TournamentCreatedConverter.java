package com.itmo.blse.tournaments.streaming.converter;

import com.itmo.blse.tournaments.model.Team;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.repository.MatchRepository;
import com.itmo.blse.tournaments.streaming.model.TournamentCreatedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TournamentCreatedConverter {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    MatchCreatedConverter matchCreatedConverter;

    public TournamentCreatedModel toEventData(Tournament tournament) {
        return TournamentCreatedModel.builder()
                .publicId(tournament.getPublicId())
                .name(tournament.getName())
                .teams(tournament.getTeams().stream().map(Team::getPublicId).collect(Collectors.toList()))
                .matches(
                        matchRepository
                                .getAllByTournament(tournament)
                                .stream()
                                .map(matchCreatedConverter::toEventData)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
