package com.example.capstone.DTO;

import jakarta.validation.constraints.NotNull;

public class ContactDetailsDTO {
	@NotNull(message = "name should not be null")
	private String name;
	@NotNull(message = "email should not be null")
	private String email;
	@NotNull(message = "details should not be null")
	private String details;

	public ContactDetailsDTO() {

	}

	public ContactDetailsDTO(String name, String email, String details) {
		super();
		this.name = name;
		this.email = email;
		this.details = details;
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String description) {
		this.details = description;
	}

}
