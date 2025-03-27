package com.example.capstone.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Data Transfer Object (DTO) for handling user login data
public class UserLoginDTO {
	@NotNull(message = "email should not be null")
	@NotBlank(message = "email should not be blank")
	private String email;
	@NotNull(message = "password should not be null")
	@NotBlank(message = "password should not be blank")
	private String password;

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

}
