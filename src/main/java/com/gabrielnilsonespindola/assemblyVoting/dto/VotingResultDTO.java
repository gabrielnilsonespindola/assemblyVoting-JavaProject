package com.gabrielnilsonespindola.assemblyVoting.dto;

import java.io.Serializable;


public class VotingResultDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String agendaTitle;
    private int yesVotes;
    private int noVotes;
    private String result;
    
    public VotingResultDTO() {
    }

	public VotingResultDTO(String id, String agendaTitle, int yesVotes, int noVotes, String result) {
		super();
		this.id = id;
		this.agendaTitle = agendaTitle;
		this.yesVotes = yesVotes;
		this.noVotes = noVotes;
		this.result = result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgendaTitle() {
		return agendaTitle;
	}

	public void setAgendaTitle(String agendaTitle) {
		this.agendaTitle = agendaTitle;
	}

	public int getYesVotes() {
		return yesVotes;
	}

	public void setYesVotes(int yesVotes) {
		this.yesVotes = yesVotes;
	}

	public int getNoVotes() {
		return noVotes;
	}

	public void setNoVotes(int noVotes) {
		this.noVotes = noVotes;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
    
	
		
		
    
	

}
