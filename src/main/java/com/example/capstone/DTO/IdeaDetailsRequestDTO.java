package com.example.capstone.DTO;

import jakarta.validation.constraints.NotNull;

public class IdeaDetailsRequestDTO {
	@NotNull(message="ideaRepo link should not be null")
	private String ideaRepo;
	@NotNull(message="ideaFiles link should not be null")
	private String ideaFiles;

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
