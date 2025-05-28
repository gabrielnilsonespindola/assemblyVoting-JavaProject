package com.gabrielnilsonespindola.assemblyVoting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gabrielnilsonespindola.assemblyVoting.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
