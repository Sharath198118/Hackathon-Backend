package com.example.capstone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.capstone.Entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
	Optional<Token> findByToken(String token);

}
