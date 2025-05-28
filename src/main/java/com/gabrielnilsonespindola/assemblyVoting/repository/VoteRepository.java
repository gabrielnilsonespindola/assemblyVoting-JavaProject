package com.gabrielnilsonespindola.assemblyVoting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;

@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {

	List<Vote> findByAgendaId(String agendaId);

	Optional<Vote> findByAgendaIdAndUserId(String agendaId, String userId);

}
