package com.example.capstone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.capstone.Entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	@Query("SELECT r from Review r where r.team.teamId=:teamId AND r.userId=:userId")
	Optional<Review> findByTeamIdAndUserId(Integer teamId, Integer userId);
}
