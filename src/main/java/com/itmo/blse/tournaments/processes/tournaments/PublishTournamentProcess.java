package com.itmo.blse.tournaments.processes.tournaments;

import com.itmo.blse.app.streaming.Event;
import com.itmo.blse.app.streaming.EventPublisher;
import com.itmo.blse.tournaments.model.Tournament;
import com.itmo.blse.tournaments.processes.AuthJavaDelegate;
import com.itmo.blse.tournaments.streaming.event.TournamentCreatedEventCreator;
import com.itmo.blse.tournaments.streaming.model.TournamentCreatedModel;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PublishTournamentProcess extends AuthJavaDelegate {

    @Autowired
    EventPublisher eventPublisher;

    @Autowired
    TournamentCreatedEventCreator tournamentCreatedEventCreator;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        super.execute(execution);
        Tournament tournament = (Tournament) execution.getVariable("tournament");
        Event<TournamentCreatedModel> event = tournamentCreatedEventCreator.createEvent(tournament);
        eventPublisher.publish(event);
        execution.setVariable("result", "success");

    }
}
