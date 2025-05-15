package com.gabrielnilsonespindola.assemblyVoting.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielnilsonespindola.assemblyVoting.domain.User;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.dto.VoteDTO;
import com.gabrielnilsonespindola.assemblyVoting.repository.VoteRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;

@Service
public class VoteService {
	
	@Autowired
	private VoteRepository repo;
	
	
	public List <Vote> findAll () {
		return repo.findAll();	
	}
	
	public Vote findById(String id) {
		Optional<Vote> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	public Vote insert(Vote obj) {
		return repo.insert(obj);
	}

	public Vote fromDTO(VoteDTO objDtO) {
		return new Vote (objDtO.getId(), objDtO.getAgenda(), objDtO.getUser(),objDtO.getVoteStatus() ,objDtO.getVoteTime() );
	}
	
	


}
