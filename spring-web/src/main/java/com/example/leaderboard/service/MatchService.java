package com.example.leaderboard.service;

import com.example.leaderboard.model.Match;
import com.example.leaderboard.model.Team;
import com.example.leaderboard.repository.MatchRepository;
import com.example.leaderboard.repository.TeamRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class MatchService {
    private MatchRepository matchRepository;
    private TeamService teamService;
    private TeamRepository teamRepository;

    @Autowired
    MatchService(MatchRepository matchRepository, TeamService teamService, TeamRepository teamRepository){

        this.matchRepository = matchRepository;
        this.teamService = teamService;
        this.teamRepository = teamRepository;
    }

    public Flux<Match> getAll(){
        return this.matchRepository.findAll();
    }

    public Mono<Match> getById(ObjectId id){
        return this.matchRepository.findById(id);
    }

    public Mono<Match> save(Match match){
        if(!match.isTie()){
            Team winnerTeam = match.getWinner();
            Team looserTeam = match.getLoosingTeam();
            winnerTeam.setWins(winnerTeam.getWins() + 1);
            winnerTeam.setScore(winnerTeam.getScore() + 3);
            looserTeam.setLoses(looserTeam.getLoses() + 1);
           // List<Team> teams = Arrays.asList(winnerTeam,looserTeam);
            this.teamService.save(winnerTeam);
            this.teamService.save(looserTeam);
           // System.out.println("here");
        }else {
            Team team1 = match.getTeam1();
            Team team2 = match.getTeam2();
            team1.setTies(team1.getTies() + 1);
            team2.setTies(team2.getTies() + 1);
            List<Team> teams = Arrays.asList(team1,team2);
            this.teamRepository.saveAll(teams);
        }

       return this.matchRepository.save(match);

    }
}
