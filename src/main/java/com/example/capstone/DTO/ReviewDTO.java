package com.example.capstone.DTO;

import jakarta.validation.constraints.NotNull;

public class ReviewDTO {
	@NotNull(message="rating should not be null")
	private Float rating;
    private int userId;
	@NotNull(message="feedback should not be null")
    private String feedback;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
