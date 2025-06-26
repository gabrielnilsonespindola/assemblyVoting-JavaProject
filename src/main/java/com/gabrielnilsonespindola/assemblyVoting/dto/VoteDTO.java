package com.gabrielnilsonespindola.assemblyVoting.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.enums.VoteStatus;

public class VoteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String agendaId;
	private String userId;
	private VoteStatus voteStatus;
	private LocalDateTime voteTime;

	public VoteDTO() {
	}

	public VoteDTO(Vote obj) {
		id = obj.getId();
		agendaId = obj.getId();
		userId = obj.getId();
		voteStatus = obj.getVoteStatus();
		voteTime = obj.getVoteTime();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(String agendaId) {
		this.agendaId = agendaId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
