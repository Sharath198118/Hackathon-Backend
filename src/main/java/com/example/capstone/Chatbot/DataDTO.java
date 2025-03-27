package com.example.capstone.Chatbot;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DataDTO {
	@NotNull(message="data should not be null")
	@NotBlank(message = "data should not be blank")
	private String data;

	public String getData() {
		return data;
	}

	public void setEmail(String data) {
		this.data = data;
	}
}
