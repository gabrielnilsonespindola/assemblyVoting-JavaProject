package com.gabrielnilsonespindola.assemblyVoting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;

@Repository
public interface AgendaRepository extends MongoRepository<Agenda, String> {

}
