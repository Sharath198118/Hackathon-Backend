package com.example.capstone.DTO;

import java.time.LocalDateTime;

import com.example.capstone.Entity.HackathonStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AdminHackathonDTO {
	private int hackathonId;
	private String name;
	private String theme;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime ideaSubmissionDeadline;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime shortListDeadline;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime implementationSubmissionDeadline;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime reviewStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime reviewEndTime;

	private String description;
	private String prizes;
	private String rules;
	private String judgingCriteria;
	private HackathonStatus hackathonStatus;
	private String firstTeamName;
	private String secondTeamName;
	private String thirdTeamName;
	private int noOfTeamsRegistered=0;

	// constructor
	public AdminHackathonDTO() {
        noOfTeamsRegistered=0;
	}

	public AdminHackathonDTO(int hackathonId, String name, String theme, LocalDateTime startDate,
			LocalDateTime ideaSubmissionDeadline, LocalDateTime shortListDeadline,
			LocalDateTime implementationSubmissionDeadline, LocalDateTime reviewStartTime, LocalDateTime reviewEndTime,
			String description, String prizes, String rules, String judgingCriteria, HackathonStatus hackathonStatus,
			String firstTeamId, String secondTeamId, String thirdTeamId, int noOfTeamsRegistered) {
		super();
		this.hackathonId = hackathonId;
		this.name = name;
		this.theme = theme;
		this.startDate = startDate;
		this.ideaSubmissionDeadline = ideaSubmissionDeadline;
		this.shortListDeadline = shortListDeadline;
		this.implementationSubmissionDeadline = implementationSubmissionDeadline;
		this.reviewStartTime = reviewStartTime;
		this.reviewEndTime = reviewEndTime;
		this.description = description;
		this.prizes = prizes;
		this.rules = rules;
		this.judgingCriteria = judgingCriteria;
		this.hackathonStatus = hackathonStatus;
		this.firstTeamName = firstTeamId;
		this.secondTeamName = secondTeamId;
		this.thirdTeamName = thirdTeamId;
		this.noOfTeamsRegistered = noOfTeamsRegistered;
	}

	public HackathonStatus getHackathonStatus() {
		return hackathonStatus;
	}

	public void setHackathonStatus(HackathonStatus hackathonStatus) {
		this.hackathonStatus = hackathonStatus;
	}

	public String getFirstTeamName() {
		return firstTeamName;
	}

	public void setFirstTeamName(String firstTeamId) {
		this.firstTeamName = firstTeamId;
	}

	public String getSecondTeamName() {
		return secondTeamName;
	}

	public void setSecondTeamName(String secondTeamId) {
		this.secondTeamName = secondTeamId;
	}

	public String getThirdTeamName() {
		return thirdTeamName;
	}

	public void setThirdTeamName(String thirdTeamId) {
		this.thirdTeamName = thirdTeamId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrizes() {
		return prizes;
	}

	public void setPrizes(String prizes) {
		this.prizes = prizes;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getJudgingCriteria() {
		return judgingCriteria;
	}

	public void setJudgingCriteria(String judgingCriteria) {
		this.judgingCriteria = judgingCriteria;
	}

	public int getHackathonId() {
		return hackathonId;
	}

	public void setHackathonId(int hackathonId) {
		this.hackathonId = hackathonId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getIdeaSubmissionDeadline() {
		return ideaSubmissionDeadline;
	}

	public void setIdeaSubmissionDeadline(LocalDateTime ideaSubmissionDeadline) {
		this.ideaSubmissionDeadline = ideaSubmissionDeadline;
	}

	public LocalDateTime getShortListDeadline() {
		return shortListDeadline;
	}

	public void setShortListDeadline(LocalDateTime shortListDeadline) {
		this.shortListDeadline = shortListDeadline;
	}

	public LocalDateTime getImplementationSubmissionDeadline() {
		return implementationSubmissionDeadline;
	}

	public void setImplementationSubmissionDeadline(LocalDateTime implementationSubmissionDeadline) {
		this.implementationSubmissionDeadline = implementationSubmissionDeadline;
	}

	public LocalDateTime getReviewStartTime() {
		return reviewStartTime;
	}

	public void setReviewStartTime(LocalDateTime reviewStartTime) {
		this.reviewStartTime = reviewStartTime;
	}

	public LocalDateTime getReviewEndTime() {
		return reviewEndTime;
	}

	public void setReviewEndTime(LocalDateTime reviewEndTime) {
		this.reviewEndTime = reviewEndTime;
	}

	public int getNoOfTeamsRegistered() {
		return noOfTeamsRegistered;
	}

	public void setNoOfTeamsRegistered(int noOfTeamsRegistered) {
		this.noOfTeamsRegistered = noOfTeamsRegistered;
	}
	

}
