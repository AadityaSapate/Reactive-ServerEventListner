package com.example.leaderboard.controller;

import com.example.leaderboard.model.Match;
import com.example.leaderboard.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/match")
public class MatchController {

    private MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService){

        this.matchService = matchService;
    }

    @PostMapping("/")
    public Mono<Match> add(@RequestBody Match match){
        return this.matchService.save(match);
    }
 }
