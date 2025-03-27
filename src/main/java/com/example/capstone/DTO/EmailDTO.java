package com.example.capstone.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmailDTO {
@NotNull(message="email should not be null")
@NotBlank(message = "email should not be blank")
private String email;

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

}
