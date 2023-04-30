package com.itmo.blse.app.streaming;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Event<T> {

    String eventId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime createdAt;
    String action;
    T data;


    public Event(T data, String action){
        this.action = action;
        this.eventId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
        this.data = data;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }


}
