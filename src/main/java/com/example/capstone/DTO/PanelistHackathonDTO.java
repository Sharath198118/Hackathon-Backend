package com.example.capstone.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

//Data Transfer Object (DTO) representing hackathon details relevant to a panelist
public class PanelistHackathonDTO {
	private int hackathonId;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime shortListStartTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime shortListEndTime;

	public PanelistHackathonDTO(int hackathonId, String name, LocalDateTime shortListStartTime,
			LocalDateTime shortListEndTime) {
		super();
		this.hackathonId = hackathonId;
		this.name = name;
		this.shortListStartTime = shortListStartTime;
		this.shortListEndTime = shortListEndTime;
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

	public LocalDateTime getShortListStartTime() {
		return shortListStartTime;
	}

	public void setShortListStartTime(LocalDateTime shortListStartTime) {
		this.shortListStartTime = shortListStartTime;
	}

	public LocalDateTime getShortListEndTime() {
		return shortListEndTime;
	}

	public void setShortListEndTime(LocalDateTime shortListEndTime) {
		this.shortListEndTime = shortListEndTime;
	}

}
