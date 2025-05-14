package com.gabrielnilsonespindola.assemblyVoting.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gabrielnilsonespindola.assemblyVoting.enums.VoteStatus;

@Document(collection = "vote")
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	private String id;
	
	@DBRef(lazy = true)
	private Agenda agenda;

	@DBRef(lazy = true)
	private User user;
	
	private VoteStatus voteStatus;
	private LocalDateTime voteTime;
	
	
	public Vote() {
	}


	public Vote(String id, Agenda agenda, User user, VoteStatus voteStatus, LocalDateTime voteTime) {
		super();
		this.id = id;
		this.agenda = agenda;
		this.user = user;
		this.voteStatus = voteStatus;
		this.voteTime = voteTime;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Agenda getAgenda() {
		return agenda;
	}


	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
