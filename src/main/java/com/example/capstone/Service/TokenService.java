package com.example.capstone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capstone.Entity.Token;
import com.example.capstone.Repository.TokenRepository;

@Service
public class TokenService {
	@Autowired
	private TokenRepository tokenRepository;

	public boolean checkToken(String token) {
		return tokenRepository.findByToken(token).isPresent();
	}

	public void blackListToken(String token) {
		Token t = new Token(token);
		tokenRepository.save(t);
	}
}
