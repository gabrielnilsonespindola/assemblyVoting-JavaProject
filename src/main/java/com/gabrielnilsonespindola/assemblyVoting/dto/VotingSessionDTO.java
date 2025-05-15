package com.gabrielnilsonespindola.assemblyVoting.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.VotingSession;

public class VotingSessionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private LocalDateTime opening;
	private LocalDateTime closure;
	private Agenda agenda;
	private Long durationInMinutes;
	

	public VotingSessionDTO() {
	}

	public VotingSessionDTO(VotingSession obj) {

		id = obj.getId();
		opening = obj.getOpening();
		closure = obj.getClosure();
		agenda = obj.getAgenda();
		durationInMinutes = obj.getDurationInMinutes();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getOpening() {
		return opening;
	}

	public void setOpening(LocalDateTime opening) {
		this.opening = opening;
	}

	public LocalDateTime getClosure() {
		return closure;
	}

	public void setClosure(LocalDateTime closure) {
		this.closure = closure;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Long getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(Long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}
	
	
	
	
	
	

}
