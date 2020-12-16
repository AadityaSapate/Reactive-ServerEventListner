package com.example.leaderboard.service;

import com.example.leaderboard.config.TeamCreatedEvent;
import com.example.leaderboard.model.Team;
import com.example.leaderboard.repository.TeamRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeamService {

    private TeamRepository teamRepository;
    private ApplicationEventPublisher publisher;


    @Autowired
    TeamService(TeamRepository teamRepository, ApplicationEventPublisher publisher){

        this.teamRepository = teamRepository;
        this.publisher = publisher;
    }

    public Flux<Team> getAll(){
        return teamRepository.findAll();
    }

    public Mono<Team> getById(ObjectId id){
        return teamRepository.findById(id);
    }

    public Mono<Team> save(Team team){
     //   System.out.println(team.getName());
        System.out.println(team);
        return teamRepository.save(team).doOnSuccess(team1 ->

                this.publisher.publishEvent(new TeamCreatedEvent(team1)
                ));
    }

//    public Mono<Team> update(Team team, long id){
//
//       teamRepository.findById(id).subscribe(team1 -> {
//            team
//        } );
//    }

}
