package com.example.capstone.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

//Entity class representing a panelist participating in a hackathon
@Entity
public class Panelist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer panelistId;
	@ManyToOne(cascade = CascadeType.ALL)
	private Hackathon hackathon;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	@OneToMany
	private List<Team> teams = new ArrayList<>();

	public Integer getPanelistId() {
		return panelistId;
	}

	public void setPanelistId(Integer panelistId) {
		this.panelistId = panelistId;
	}

	public Hackathon getHackathon() {
		return hackathon;
	}

	public void setHackathon(Hackathon hackathon) {
		this.hackathon = hackathon;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Team> getTeam() {
		return teams;
	}

	public void setTeam(Team team) {
		teams.add(team);
	}

}
