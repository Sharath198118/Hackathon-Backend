package com.example.capstone.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.capstone.DTO.JudgeHackathonDTO;
import com.example.capstone.DTO.ReviewDTO;
import com.example.capstone.DTO.TeamDetailsToJudgeDTO;
import com.example.capstone.Entity.Hackathon;
import com.example.capstone.Entity.Judge;
import com.example.capstone.Entity.Review;
import com.example.capstone.Entity.Status;
import com.example.capstone.Entity.Team;
import com.example.capstone.Entity.User;
import com.example.capstone.Exceptions.ResourceNotFoundException;
import com.example.capstone.Exceptions.UnauthorizedException;
import com.example.capstone.Repository.JudgeRepository;

//Service class for judge-related operations.
@Service
public class JudgeService {

	// Autowired repositories and services to interact with the database and other
	// services
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private HackathonService hackathonService;

	@Autowired
	private JudgeRepository judgeRepository;

	@Value("${custom.feature.isDevelopment}")
	private boolean isDevelopment;
	

	@Value("${custom.feature.originTimeZone}")
	private String originTimeZone;

	@Value("${custom.feature.destinationTimeZone}")
	private String destinationTimeZone;

	

	/**
	 * Creates a new Judge entity for a given User and Hackathon.
	 * 
	 * @param user      The User who will be assigned as a Judge.
	 * @param hackathon The Hackathon for which the User will be judging.
	 * @return Judge The newly created Judge entity.
	 */
	public Judge createJudge(User user, Hackathon hackathon) {
		Judge judge = new Judge();
		judge.setHackthon(hackathon);
		judge.setUser(user);
		return judge;
	}

	/**
	 * Delegates to the ReviewService to add a review for a specific team.
	 * 
	 * @param teamId    The id of the team being reviewed.
	 * @param reviewDTO The data transfer object containing review details.
	 */
	public void addReview(int teamId, ReviewDTO reviewDTO) {
		reviewService.addReview(teamId, reviewDTO);
	}

	/**
	 * Retrieves a JudgeHackathonDTO which includes hackathon details assigned to a
	 * judge.
	 * 
	 * @param judgeId The identifier of the Judge.
	 * @return JudgeHackathonDTO The transfer object containing assigned hackathon
	 *         details for a judge.
	 */
	public JudgeHackathonDTO getJudgeHackathonDTO(int judgeId) {
		return judgeRepository.findAssignedHackathon(judgeId);
	}

	/**
	 * Fetches a list of teams selected for a particular hackathon for judging
	 * purposes.
	 * 
	 * @param hackathonId The identifier of the Hackathon.
	 * @return List<TeamDetailsToJudgeDTO> A list of data transfer objects
	 *         containing team details.
	 */
	public List<TeamDetailsToJudgeDTO> getSelectedTeamsDetails(int hackathonId) {

		Hackathon hackathon = hackathonService.findHackathon(hackathonId);
		LocalDateTime currentTime = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
		if (isDevelopment || currentTime.isAfter(hackathon.getReviewStartTime())
				&& currentTime.isBefore(hackathon.getReviewEndTime())) {
			List<Team> teams = judgeRepository.findTeams(hackathonId);
			List<TeamDetailsToJudgeDTO> detailsToJudgeDTOs = new ArrayList<>();
			for (Team team : teams) {
				if (team.getStatus().equals(Status.implemented)) {

					TeamDetailsToJudgeDTO teamDetailsToJudgeDTO = new TeamDetailsToJudgeDTO();
					teamDetailsToJudgeDTO.setTeamName(team.getName());
					teamDetailsToJudgeDTO.setIdeaBody(team.getIdeaBody());
					teamDetailsToJudgeDTO.setIdeaDomain(team.getIdeaDomain());
					teamDetailsToJudgeDTO.setIdeaFiles(team.getIdeaFiles());
					teamDetailsToJudgeDTO.setIdeaRepo(team.getIdeaRepo());
					teamDetailsToJudgeDTO.setIdeaTitle(team.getIdeaTitle());
					teamDetailsToJudgeDTO.setTeamId(team.getTeamId());
					teamDetailsToJudgeDTO.setStatus(team.getStatus());
					for (Review review : team.getReviews()) {
						teamDetailsToJudgeDTO.getUserIds().add(review.getUserId());
					}
					detailsToJudgeDTOs.add(teamDetailsToJudgeDTO);
				}
			}

			if (detailsToJudgeDTOs.size() > 0) {
				return detailsToJudgeDTOs;
			} else {
				throw new ResourceNotFoundException("No team implemented the idea");
			}
		} else if (currentTime.isAfter(hackathon.getReviewEndTime())) {
			throw new UnauthorizedException("Reviewing has been ended");
		} else {
			throw new UnauthorizedException("Review not started");
		}
	}
}
