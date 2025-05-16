package com.gabrielnilsonespindola.assemblyVoting.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.User;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.enums.VoteStatus;

public class VoteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private Agenda agendaId;
	private User userId;
	private VoteStatus voteStatus;
	private LocalDateTime voteTime;

	public VoteDTO() {
	}
	
	public VoteDTO(Vote obj) {
		id = obj.getId();
		agendaId = obj.getAgenda();
		userId = obj.getUser();
		voteStatus = obj.getVoteStatus();
		voteTime = obj.getVoteTime();
				
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Agenda getAgenda() {
		return agendaId;
	}

	public void setAgenda(Agenda agenda) {
		this.agendaId = agenda;
	}

	public User getUser() {
		return userId;
	}

	public void setUser(User user) {
		this.userId = user;
	}

	public VoteStatus getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(VoteStatus voteStatus) {
		this.voteStatus = voteStatus;
	}

	public LocalDateTime getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(LocalDateTime voteTime) {
		this.voteTime = voteTime;
	}
	
	
	
	

	

}
