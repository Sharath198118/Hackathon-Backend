package com.example.capstone.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Data Transfer Object (DTO) for representing user data
public class UserDTO {
	@NotNull(message="email should not be null")
	@NotBlank(message = "email should not be blank")
	private String email;
	@NotNull(message="name should not be null")
	@NotBlank(message = "name should not be blank")
	private String name;
	@NotNull(message="password should not be null")
	@NotBlank(message = "password should not be blank")
	private String password;

	public UserDTO() {

	}

	public UserDTO(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
