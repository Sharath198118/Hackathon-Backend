package com.example.capstone.DTO;

import com.example.capstone.Entity.Status;

//Data Transfer Object (DTO) representing team details for a panelist
public class TeamDetailsToPanelistDTO {

	private Integer teamId;
	private String ideaBody;
	private String ideaDomain;
	private String ideaTitle;
	private String teamName;
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getIdeaBody() {
		return ideaBody;
	}

	public void setIdeaBody(String ideaBody) {
		this.ideaBody = ideaBody;
	}

	public String getIdeaDomain() {
		return ideaDomain;
	}

	public void setIdeaDomain(String ideaDomain) {
		this.ideaDomain = ideaDomain;
	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
