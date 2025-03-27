package com.example.capstone.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.capstone.Entity.Status;

public class TeamDetailsDTO {
	private int teamId;
	private String name;
	private String ideaTitle;
	private String ideaRepo;
	private String ideaFiles;
	private String ideaBody;
	private Status status;
	private String ideaDomain;
	private Float consolidatedRating;
	private int hackathonId;
	private List<TeamUserDetailsDTO> teamUserDetailsDTOs=new ArrayList<>();
	private List<String> feedBacks=new ArrayList<>();
  
	public TeamDetailsDTO() {
		teamUserDetailsDTOs = new ArrayList<>();
		consolidatedRating = null;
	}
    
	public String getIdeaBody() {
		return ideaBody;
	}

	public void setIdeaBody(String ideaBody) {
		this.ideaBody = ideaBody;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public String getIdeaRepo() {
		return ideaRepo;
	}

	public void setIdeaRepo(String ideaRepo) {
		this.ideaRepo = ideaRepo;
	}

	public String getIdeaFiles() {
		return ideaFiles;
	}

	public void setIdeaFiles(String ideaFiles) {
		this.ideaFiles = ideaFiles;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getIdeaDomain() {
		return ideaDomain;
	}

	public void setIdeaDomain(String ideaDomain) {
		this.ideaDomain = ideaDomain;
	}

	public Float getConsolidatedRating() {
		return consolidatedRating;
	}

	public void setConsolidatedRating(Float consolidatedRating) {
		this.consolidatedRating = consolidatedRating;
	}

	public int getHackathonId() {
		return hackathonId;
	}

	public void setHackathonId(int hackathonId) {
		this.hackathonId = hackathonId;
	}

	public List<TeamUserDetailsDTO> getTeamUserDetailsDTOs() {
		return teamUserDetailsDTOs;
	}

	public void setTeamUserDetailsDTOs(List<TeamUserDetailsDTO> teamUserDetailsDTOs) {
		this.teamUserDetailsDTOs = teamUserDetailsDTOs;
	}

	public List<String> getFeedBacks() {
		return feedBacks;
	}

	public void setFeedBacks(List<String> feedBacks) {
		this.feedBacks = feedBacks;
	}

}
