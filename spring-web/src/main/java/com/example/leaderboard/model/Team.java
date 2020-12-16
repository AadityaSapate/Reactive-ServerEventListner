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

import java.util.List;

@Document("teams")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
//    @Transient
//    public static final String SEQUENCE = "team_sequence";
//
//
//    @Id
     ObjectId id;
     String name;

    Integer wins = 0;

    Integer loses = 0 ;

    Integer ties = 0;

    Integer score = 0;


}
