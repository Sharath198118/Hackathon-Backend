package com.example.capstone.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Judge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer judgeId;

	@ManyToOne
	private User user;
	@ManyToOne
	private Hackathon hackathon;

	public Integer getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(Integer judgeId) {
		this.judgeId = judgeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hackathon getHackathon() {
		return hackathon;
	}

	public void setHackthon(Hackathon hackthon) {
		this.hackathon = hackthon;
	}

}
