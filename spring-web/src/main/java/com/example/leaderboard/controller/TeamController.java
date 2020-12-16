package com.example.leaderboard.controller;

import com.example.leaderboard.model.Team;
import com.example.leaderboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@CrossOrigin("*")
@RequestMapping("/teams")
public class TeamController {
   private TeamService teamService;
     @Autowired
    TeamController(TeamService teamService){

         this.teamService = teamService;
     }

     @GetMapping("/")
     public Flux<Team> getAll(){
      return teamService.getAll();
     }

     @PostMapping("/")
     public Mono<Team> add(@RequestBody Team team){
         return teamService.save(team);
     }
}
