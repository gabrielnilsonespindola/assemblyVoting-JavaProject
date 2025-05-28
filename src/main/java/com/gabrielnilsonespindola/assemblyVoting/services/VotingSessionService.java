package com.gabrielnilsonespindola.assemblyVoting.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Service;

import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.domain.VotingSession;
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingResultDTO;
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingSessionDTO;
import com.gabrielnilsonespindola.assemblyVoting.enums.VoteStatus;
import com.gabrielnilsonespindola.assemblyVoting.repository.VoteRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.VotingSessionRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;

@Service
public class VotingSessionService {

	@Autowired
	private VotingSessionRepository repo;

	@Autowired
	private VoteRepository repoVote;

	@DBRef
	private Agenda agenda;

	@Autowired
	private AgendaService service;

	public List<VotingSession> findAll() {
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
		return new VotingSession(objDtO.getId(), objDtO.getOpening(), objDtO.getClosure(),
				objDtO.getDurationInMinutes(), objDtO.getAgenda());
	}

	public VotingSession startSession(String agendaId, Optional<Long> durationInMinutes) {
		Agenda agenda = service.findById(agendaId);

		VotingSession session = new VotingSession();
		session.setAgenda(agenda);
		session.setOpening(LocalDateTime.now());

		long duration = durationInMinutes.orElse(1L);
		session.setClosure(session.getOpening().plusMinutes(duration));

		return repo.save(session);
	}

	public VotingResultDTO getVotingResult(String agendaId) {

		Agenda agenda = service.findById(agendaId);
		List<Vote> votes = repoVote.findByAgendaId(agendaId);

		int yes = 0;
		int no = 0;

		for (Vote vote : votes) {
			VoteStatus status = vote.getVoteStatus();
			if (status == VoteStatus.YES) {
				yes++;
			} else if (status == VoteStatus.NO) {
				no++;
			}
		}

		String result;
		if (yes > no) {
			result = "Aprovado";
		} else if (no > yes) {
			result = "Rejeitado";
		} else {
			result = "Empate";
		}

		return new VotingResultDTO(agenda.getId(), agenda.getTitle(), yes, no, result);
	}

}
