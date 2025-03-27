package com.example.capstone.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.capstone.Entity.Status;

public class TeamDetailsToJudgeDTO {
	private Integer teamId;
	private String ideaRepo;
	private String ideaFiles;
	private String ideaBody;
	private String ideaDomain;
	private String ideaTitle;
	private String teamName;
	private Status status;
	private List<Integer> userIds = new ArrayList<>();

	public TeamDetailsToJudgeDTO() {

	}

	public TeamDetailsToJudgeDTO(Integer teamId, String ideaRepo, String ideaFiles, String ideaBody, String ideaDomain,
			String ideaTitle, String teamName, Status status) {
		super();
		this.teamId = teamId;
		this.ideaRepo = ideaRepo;
		this.ideaFiles = ideaFiles;
		this.ideaBody = ideaBody;
		this.ideaDomain = ideaDomain;
		this.ideaTitle = ideaTitle;
		this.teamName = teamName;
		this.status = status;
	}

	public List<Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
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

}
