package com.gabrielnilsonespindola.assemblyVoting.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class VotingSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private LocalDateTime opening;
	private LocalDateTime closure;
	private Agenda agenda;
	private Long durationInMinutes;	
	

	@DBRef(lazy = true)
	private List<Vote> votes = new ArrayList<>();

	public VotingSession() {
	}

	public VotingSession(String id, LocalDateTime opening, LocalDateTime closure, Long durationInMinutes,
			Agenda agenda) {
		super();
		this.id = id;
		this.opening = opening;
		this.closure = closure;
		this.durationInMinutes = durationInMinutes;
		this.agenda = agenda;
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

	public Long getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(Long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
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
		VotingSession other = (VotingSession) obj;
		return Objects.equals(id, other.id);
	}

}
