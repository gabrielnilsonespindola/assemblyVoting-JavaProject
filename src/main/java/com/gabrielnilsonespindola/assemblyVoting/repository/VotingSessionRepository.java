package com.gabrielnilsonespindola.assemblyVoting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielnilsonespindola.assemblyVoting.domain.VotingSession;

@Repository
public interface VotingSessionRepository extends MongoRepository<VotingSession, String> {

}
