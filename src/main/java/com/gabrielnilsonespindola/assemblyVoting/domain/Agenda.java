package com.gabrielnilsonespindola.assemblyVoting.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gabrielnilsonespindola.assemblyVoting.enums.VoteStatus;

@Document(collection = "agenda")
public class Agenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String title;
	private Integer number;
	private String descricao;
	private LocalDate date;

	@DBRef(lazy = true)
	private List<Vote> votes = new ArrayList<>();

	public Agenda() {
	}

	public Agenda(String id, String title, Integer number, LocalDate date) {
		this.id = id;
		this.title = title;
		this.number = number; // Construtor responsavel do AgendaDTO- InsertFormulary New Agenda.
		this.date = date;
	}

	public Agenda(String id, String title, Integer number, String descricao, LocalDate date, List<Vote> votes) {
		super();
		this.id = id;
		this.title = title;
		this.number = number;
		this.descricao = descricao;
		this.date = date;
		this.votes = votes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Vote> getVotes() {
		return votes;
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
		Agenda other = (Agenda) obj;
		return Objects.equals(id, other.id);
	}

}
