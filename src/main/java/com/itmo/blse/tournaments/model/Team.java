package com.itmo.blse.tournaments.model;

import com.itmo.blse.app.model.Timestamped;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
public class Team extends Timestamped {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private UUID publicId;

    @PrePersist
    public void generateUuid() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }

}