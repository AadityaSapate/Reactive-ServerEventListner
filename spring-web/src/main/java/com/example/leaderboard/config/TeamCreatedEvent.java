package com.example.leaderboard.config;

import com.example.leaderboard.model.Team;
import org.springframework.context.ApplicationEvent;

public class TeamCreatedEvent extends ApplicationEvent {
    public TeamCreatedEvent(Team source) {
        super(source);
    }
}
