package com.example.capstone.DTO;

import jakarta.validation.constraints.NotNull;

//Data Transfer Object (DTO) for handling email verification data
public class EmailVerificationDTO {
	@NotNull(message = "email should not be null")
	private String email;
	@NotNull(message = "otp should not be null")
	private String otp;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setPassword(String otp) {
		this.otp = otp;
	}

}
