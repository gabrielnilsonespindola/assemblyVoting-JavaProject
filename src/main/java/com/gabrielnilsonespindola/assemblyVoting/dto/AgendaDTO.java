package com.gabrielnilsonespindola.assemblyVoting.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;

public class AgendaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	private Integer number;
	private LocalDate date;

	public AgendaDTO() {
	}

	public AgendaDTO(Agenda obj) {

		id = obj.getId();
		title = obj.getTitle();
		number = obj.getNumber();
		date = obj.getDate();
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
