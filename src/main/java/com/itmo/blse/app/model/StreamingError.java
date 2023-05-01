package com.itmo.blse.app.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="streaming_error")
public class StreamingError extends Timestamped{

    @Column(columnDefinition="TEXT")
    private String message;

    @Column(columnDefinition="TEXT")
    private String errorText;

    @Column
    private String eventId;

    @Column
    private String eventAction;


}
