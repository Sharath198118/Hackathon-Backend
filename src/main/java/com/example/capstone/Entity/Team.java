package com.example.capstone.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

// This class represents a team in the system. It includes fields for the team's name, idea details,
// participants, panelist, and hackathon.

// This class is annotated with @Entity to indicate that it is a JPA entity. It also includes
// various JPA annotations to define the relationships between the team and other entities.
@Entity
public class Team {
//The team's primary key is the teamId field, which is generated automatically.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teamId;
	@Column(nullable = true)
	private String name;
	@Column(nullable = true)
	private String ideaTitle;
	@Lob
	@Column(nullable = true, length = 3000)
	private String ideaBody;
	@Column(nullable = true)
	private String ideaRepo;
	@Column(nullable = true)
	private String ideaFiles;
	@Column(nullable = true)
	private String ideaDomain;
	@Column(nullable = true)
	private Float consolidatedRating;
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(cascade = CascadeType.ALL)
	@Column
	private List<Review> reviews;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Participant> participants;

	@ManyToOne
	private Panelist panelist;

	@ManyToOne
	private Hackathon hackathon;

	public Team() {
		reviews = new ArrayList<>();
		participants = new ArrayList<>();

	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
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

	public String getIdeaBody() {
		return ideaBody;
	}

	public void setIdeaBody(String ideaBody) {
		this.ideaBody = ideaBody;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Panelist getPanelist() {
		return panelist;
	}

	public void setPanelist(Panelist panelist) {
		this.panelist = panelist;
	}

	public Hackathon getHackathon() {
		return hackathon;
	}

	public void setHackathon(Hackathon hackathon) {
		this.hackathon = hackathon;
	}

}
