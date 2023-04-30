package com.itmo.blse.tournaments.model;

import com.itmo.blse.app.model.Timestamped;
import com.itmo.blse.users.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tournament")
public class Tournament extends Timestamped {


    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private double approvalRatio;

    @ManyToOne
    private Team winner;

    @Column(nullable = false)
    private int maxGames;

    @ManyToMany
    private List<User> judges;

    @ManyToMany
    private List<Team> teams;

    @Column(unique = true, nullable = false)
    private UUID publicId;

    @PrePersist
    public void generateUuid() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }


}