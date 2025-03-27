package com.example.capstone.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//Entity class representing users in the system
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;
    
	private String domain;
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	private boolean isAvailable;
	
	@Enumerated(EnumType.STRING)
	private TypeOfRegistered ofRegistered;

	private Integer assignedHackathon;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Participant> participants;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Panelist> panelists;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Judge> judges;
    public User()
    {
    	
    }
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Participant participant) {
		participants.add(participant);
	}

	public List<Panelist> getPanelists() {
		return panelists;
	}

	public void setPanelists(List<Panelist> panelists) {
		this.panelists = panelists;
	}

	public List<Judge> getJudges() {
		return judges;
	}

	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}

	public Integer getAssignedHackathon() {
		return assignedHackathon;
	}

	public void setAssignedHackathon(Integer assignedHackathon) {
		this.assignedHackathon = assignedHackathon;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public TypeOfRegistered getOfRegistered() {
		return ofRegistered;
	}

	public void setOfRegistered(TypeOfRegistered ofRegistered) {
		this.ofRegistered = ofRegistered;
	}
	

}
