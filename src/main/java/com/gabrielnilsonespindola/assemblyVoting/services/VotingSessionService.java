package com.gabrielnilsonespindola.assemblyVoting.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gabrielnilsonespindola.assemblyVoting.domain.VotingSession;
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingSessionDTO;
import com.gabrielnilsonespindola.assemblyVoting.repository.VotingSessionRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;

public class VotingSessionService {
	
	@Autowired
	private VotingSessionRepository repo;
	
	
	public List <VotingSession> findAll () {
		return repo.findAll();	
	}
	
	public VotingSession findById(String id) {
		Optional<VotingSession> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
	
	public VotingSession insert(VotingSession obj) {
		return repo.insert(obj);
	}

	public VotingSession fromDTO(VotingSessionDTO objDtO) {
		return new VotingSession (objDtO.getId(), objDtO.getOpening(), objDtO.getClosure(),objDtO.getAgenda());
	}
	
	


}



