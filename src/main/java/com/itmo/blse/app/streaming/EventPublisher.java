package com.itmo.blse.app.streaming;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventPublisher {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void publish(Event<?> event) {
        try {
            String message = event.toJson();
            jmsTemplate.convertAndSend(message);
        }
        catch (JsonProcessingException ex){
            log.error(String.format("Event %s cannot be serialized to JSON", event.getEventId()));
            throw new RuntimeException(ex);
        }
    }
}
