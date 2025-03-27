package com.example.capstone.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JudgeHackathonDTO {
	private int hackathonId;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime reviewStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime reviewEndTime;

	private String judgingCriteria;

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

	public String getJudgingCriteria() {
		return judgingCriteria;
	}

	public void setJudgingCriteria(String judgingCriteria) {
		this.judgingCriteria = judgingCriteria;
	}

	public JudgeHackathonDTO(int hackathonId, String name, LocalDateTime reviewStartTime, LocalDateTime reviewEndTime,
			String judgingCriteria) {
		super();
		this.hackathonId = hackathonId;
		this.name = name;
		this.reviewStartTime = reviewStartTime;
		this.reviewEndTime = reviewEndTime;
		this.judgingCriteria = judgingCriteria;
	}

}
