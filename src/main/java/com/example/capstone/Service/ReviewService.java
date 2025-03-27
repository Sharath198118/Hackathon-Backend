package com.example.capstone.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.capstone.DTO.ReviewDTO;
import com.example.capstone.Entity.Hackathon;
import com.example.capstone.Entity.Review;
import com.example.capstone.Entity.Team;
import com.example.capstone.Exceptions.ConflictException;
import com.example.capstone.Exceptions.UnauthorizedException;
import com.example.capstone.Repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private TeamService teamService;
	@Autowired
	private ReviewRepository reviewRepository;
	@Value("${custom.feature.isDevelopment}")
	private boolean isDevelopment;

	@Value("${custom.feature.originTimeZone}")
	private String originTimeZone;

	@Value("${custom.feature.destinationTimeZone}")
	private String destinationTimeZone;

	
	public void addReview(int teamid, ReviewDTO reviewDTO) {
		Optional<Review> reviewOptional = reviewRepository.findByTeamIdAndUserId(teamid, reviewDTO.getUserId());
		if (reviewOptional.isEmpty()) {
			Team team = teamService.getTeam(teamid);
			Hackathon hackathon = team.getHackathon();
			LocalDateTime currentTime = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
			if (isDevelopment || currentTime.isAfter(hackathon.getReviewStartTime())
					&& currentTime.isBefore(hackathon.getReviewEndTime())) {
				Review review = new Review();
				review.setRating(reviewDTO.getRating());
				review.setTeam(team);
				review.setFeedBack(reviewDTO.getFeedback());
				review.setUserId(reviewDTO.getUserId());
				team.getReviews().add(review);
				teamService.updateTeam(team);
			} else if (currentTime.isAfter(hackathon.getReviewEndTime())) {
				throw new UnauthorizedException("Reviewing has been ended");
			} else {
				throw new UnauthorizedException("Review not started");
			}
		} else {
			throw new ConflictException("Team already reviewed");
		}
	}

}
