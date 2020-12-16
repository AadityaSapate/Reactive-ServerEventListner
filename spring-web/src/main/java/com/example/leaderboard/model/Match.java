package com.example.leaderboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("matches")
@Setter
@Getter
@AllArgsConstructor
public class Match {
//    @Transient
//    public static final String SEQUENCE = "match_sequence";
//    @Id
    ObjectId id;

    @DBRef
    Team team1;

    @DBRef
    Team team2;

    @DBRef
    Team winner;

    @DBRef
    Team loosingTeam;

    boolean tie;
}
