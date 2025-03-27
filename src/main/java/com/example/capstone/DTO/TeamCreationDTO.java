package com.example.capstone.DTO;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class TeamCreationDTO {
	@NotNull(message = "name should not be null")
	private String name;
	private List<String> emails = new ArrayList<>();

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
