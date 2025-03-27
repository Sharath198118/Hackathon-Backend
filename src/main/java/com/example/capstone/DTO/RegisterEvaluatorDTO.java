package com.example.capstone.DTO;

import com.example.capstone.Entity.Role;

import jakarta.validation.constraints.NotNull;

public class RegisterEvaluatorDTO {
	@NotNull(message = "email should not be null")
	private String email;
	@NotNull(message = "name should not be null")
	private String name;
	@NotNull(message = "role should not be null")
	private Role role;
	@NotNull(message = "domain should not be null")
	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
