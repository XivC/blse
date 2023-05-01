package com.itmo.blse.app.streaming;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itmo.blse.app.model.StreamingError;
import com.itmo.blse.app.repository.StreamingErrorRepository;
import com.rabbitmq.client.AuthenticationFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.transaction.Transactional;
import java.io.IOException;

@Component
@Slf4j
public class EventPublisher {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private StreamingErrorRepository streamingErrorRepository;

    @Transactional
    public void publish(Event<?> event) {
        String message;
        try {
            message = event.toJson();
        }
        catch (JsonProcessingException ex){
            log.error(String.format("Event %s cannot be serialized to JSON", event.getEventId()));
            throw new RuntimeException(ex);
        }
        try {
            jmsTemplate.send(session -> session.createTextMessage(message));
        }
        catch (Throwable ex){
            StreamingError error = StreamingError.builder()
                    .eventId(event.getEventId())
                    .eventAction(event.getAction())
                    .message(message)
                    .errorText(ex.getMessage())
                    .build();
            streamingErrorRepository.save(error);
            log.error(
                    String.format(
                            "Event %s for action %s was not published. Check streaming_error %s",
                                  event.getEventId(), event.getAction(), error.getId()
                            )
            );
        }


    }
}
