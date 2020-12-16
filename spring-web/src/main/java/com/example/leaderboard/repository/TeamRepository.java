package com.example.leaderboard.repository;

import com.example.leaderboard.model.Team;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends ReactiveMongoRepository<Team, ObjectId> {
}
