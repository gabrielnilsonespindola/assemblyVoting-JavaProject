package com.gabrielnilsonespindola.assemblyVoting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;

@Repository
public interface VoteRepository extends MongoRepository<Vote , String> {
	
	

}
