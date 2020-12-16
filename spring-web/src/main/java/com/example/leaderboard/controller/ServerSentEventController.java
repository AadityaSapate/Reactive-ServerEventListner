package com.example.leaderboard.controller;


import com.example.leaderboard.config.TeamCreatedEvent;

import com.example.leaderboard.config.TeamCreatedEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerSentEventController {
    private final Flux<TeamCreatedEvent> events;
    private final ObjectMapper objectMapper;

    public ServerSentEventController(TeamCreatedEventPublisher eventPublisher, ObjectMapper objectMapper) {
        this.events = Flux.create(eventPublisher).share();
        this.objectMapper = objectMapper;
    }

    @GetMapping(path = "/sse/teams", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "*")
    public Flux<String> profiles() {
        return this.events.map(pce -> {
            try {
                return objectMapper.writeValueAsString(pce) + "\n\n";
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
