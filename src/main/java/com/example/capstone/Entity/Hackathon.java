package com.example.capstone.Entity;
 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
 
@Entity
public class Hackathon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hackathonId;
	private String name;
	private String theme;
	private LocalDateTime startDate;
	private LocalDateTime ideaSubmissionDeadline;
	private LocalDateTime shortListDeadLine;
	private LocalDateTime implementationSubmissionDeadLine;
	private LocalDateTime reviewStartTime;
	private LocalDateTime reviewEndTime;
 
	@Lob
	@Column(length = 3000)
	private String description;
	@Lob
	@Column(length = 3000)
	private String prizes;
	@Lob
	@Column(length = 3000)
	private String rules;
	@Lob
	@Column(length = 3000)
	private String judging_criteria;
	private String firstTeamId;
	private String secondTeamId;
	private String thirdTeamId;
 
	@Enumerated(EnumType.STRING)
	private HackathonStatus hackathonStatus;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Judge> judges = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Panelist> panelists = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Team> teams = new ArrayList<>();
 
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
 
	public String getJudging_criteria() {
		return judging_criteria;
	}
 
	public void setJudging_criteria(String judging_criteria) {
		this.judging_criteria = judging_criteria;
	}
 
	public Integer getHackathonId() {
		return hackathonId;
	}
 
	public void setHackathonId(Integer hackathonId) {
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
 
	public LocalDateTime getShortListDeadLine() {
		return shortListDeadLine;
	}
 
	public void setShortListDeadLine(LocalDateTime shortListDeadLine) {
		this.shortListDeadLine = shortListDeadLine;
	}
 
	public LocalDateTime getImplementationSubmissionDeadLine() {
		return implementationSubmissionDeadLine;
	}
 
	public void setImplementationSubmissionDeadLine(LocalDateTime implementationSubmissionDeadLine) {
		this.implementationSubmissionDeadLine = implementationSubmissionDeadLine;
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
 
	public String getFirstTeamId() {
		return firstTeamId;
	}
 
	public void setFirstTeamId(String firstTeamId) {
		this.firstTeamId = firstTeamId;
	}
 
	public String getSecondTeamId() {
		return secondTeamId;
	}
 
	public void setSecondTeamId(String secondTeamId) {
		this.secondTeamId = secondTeamId;
	}
 
	public String getThirdTeamId() {
		return thirdTeamId;
	}
 
	public void setThirdTeamId(String thirdTeamId) {
		this.thirdTeamId = thirdTeamId;
	}
 
	public HackathonStatus getHackathonStatus() {
		return hackathonStatus;
	}
 
	public void setHackathonStatus(HackathonStatus hackathonStatus) {
		this.hackathonStatus = hackathonStatus;
	}
 
	public List<Judge> getJudges() {
		return judges;
	}
 
	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}
 
	public List<Panelist> getPanelists() {
		return panelists;
	}
 
	public void setPanelists(List<Panelist> panelists) {
		this.panelists = panelists;
	}
 
	public List<Team> getTeams() {
		return teams;
	}
 
	public void setTeams(Team teams) {
		this.teams.add(teams);
	}
 
}