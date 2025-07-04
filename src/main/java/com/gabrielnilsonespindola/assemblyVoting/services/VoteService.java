package com.gabrielnilsonespindola.assemblyVoting.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.User;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.dto.VoteDTO;
import com.gabrielnilsonespindola.assemblyVoting.repository.VoteRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VoteService {

	@Autowired
	private VoteRepository repo;

	@Autowired
	private AgendaService agendaService;

	@Autowired
	private UserService userService;

	public List<Vote> findAll() {
		return repo.findAll();
	}

	public Vote findById(String id) {
		Optional<Vote> obj = repo.findById(id);
		return obj.orElseThrow(() -> {
	        log.error("Vote com ID {} não encontrado", id);
	        return new ObjectNotFoundException("Objeto/Vote não encontrado");
	    });
	}

	public Vote insert(Vote obj) {
		return repo.insert(obj);
	}

	public Vote fromDTO(VoteDTO dto) {
		Agenda agenda = agendaService.findById(dto.getAgendaId());
	    User user = userService.findById(dto.getUserId());

	    LocalDateTime voteTime = Optional.ofNullable(dto.getVoteTime()).orElse(LocalDateTime.now());

	    return new Vote(null, agenda, user, dto.getVoteStatus(), voteTime);
	}

	public Vote registerVote(Vote obj) {
		String agendaId = obj.getAgenda().getId();
		String userId = obj.getUser().getId();

		Optional<Vote> existingVote = repo.findByAgendaIdAndUserId(agendaId, userId);

		if (existingVote.isPresent()) {
			log.warn("USUARIO JA VOTOU NESTA PAUTA {}" , existingVote.isPresent());
			throw new IllegalArgumentException("Usuário já votou nesta pauta.");
		}

		return repo.save(obj);
	}
}
