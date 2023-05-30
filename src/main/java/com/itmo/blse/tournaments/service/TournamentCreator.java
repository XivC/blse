package com.itmo.blse.tournaments.service;


import com.itmo.blse.app.error.ValidationError;
import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.app.streaming.EventPublisher;
import com.itmo.blse.tournaments.dto.CreateTournamentDto;
import com.itmo.blse.tournaments.model.Roles;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.repository.TournamentRepository;
import com.itmo.blse.tournaments.streaming.event.TournamentCreatedEventCreator;
import com.itmo.blse.tournaments.streaming.model.TournamentCreatedModel;
import com.itmo.blse.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TournamentCreator {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    MatchesTreeBuilder matchesTreeBuilder;

    @Autowired
    TournamentCreatedEventCreator tournamentCreatedEventCreator;

    @Autowired
    EventPublisher eventPublisher;


    @Transactional
    public Tournament create(CreateTournamentDto data) throws ValidationError {
        validate(data);
        Tournament tournament = Tournament
                .builder()
                .name(data.getName())
                .maxGames(data.getMaxGames())
                .approvalRatio(data.getApprovalRatio())
                .startDate(data.getStartDate())
                .judges(data.getJudges())
                .teams(data.getTeams())
                .build();

        tournamentRepository.save(tournament);


        return tournamentRepository.getTournamentById(tournament.getId());  // refresh from db


    }

    private void validate(CreateTournamentDto data) throws ValidationError {
        List<String> errors = new ArrayList<>();
        for (User user: data.getJudges()){
            if (!user.hasRole(Roles.JUDGE)){
                errors.add(String.format("User %s is not a judge", user.getId()));
            }
        }
        if (errors.size() > 0){
            throw new ValidationError(errors);
        }
    }


}
