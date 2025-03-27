package com.example.capstone.Service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class PasswordGenerationService {
	private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

	public String generatePassword() {
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder password = new StringBuilder();

		for (int i = 0; i < 10; i++) {
			int index = secureRandom.nextInt(characters.length());
			password.append(characters.charAt(index));
		}

		return password.toString();
	}
}
