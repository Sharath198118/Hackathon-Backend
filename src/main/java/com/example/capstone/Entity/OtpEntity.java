package com.example.capstone.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OtpEntity {
	@Id
	private String email;
	private String name;
	private String password;
	private String otp;

	public OtpEntity() {

	}

	public OtpEntity(String email, String name, String password, String otp) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.otp = otp;
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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
