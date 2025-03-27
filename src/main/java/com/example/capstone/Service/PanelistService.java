package com.example.capstone.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.capstone.DTO.PanelistHackathonDTO;
import com.example.capstone.DTO.TeamDetailsToPanelistDTO;
import com.example.capstone.Entity.Hackathon;
import com.example.capstone.Entity.Panelist;
import com.example.capstone.Entity.User;
import com.example.capstone.Exceptions.HackathonNotExistsException;
import com.example.capstone.Exceptions.TeamNotFoundException;
import com.example.capstone.Exceptions.UnauthorizedException;
import com.example.capstone.Exceptions.UserNotFoundException;
import com.example.capstone.Repository.PanelistRepository;

//Service to manage panelist-related operations
@Service
public class PanelistService {
	@Autowired
	private PanelistRepository panelistRepository;
	@Autowired
	private UserService userService;
	@Value("${custom.feature.isDevelopment}")
	private boolean isDevelopment;

	
	@Value("${custom.feature.originTimeZone}")
	private String originTimeZone;

	@Value("${custom.feature.destinationTimeZone}")
	private String destinationTimeZone;

	

	// Create a new panelist
	public Panelist createPanelist(User user, Hackathon hackathon) {
		Panelist panelist = new Panelist();
		panelist.setHackathon(hackathon);
		panelist.setUser(user);
		panelistRepository.save(panelist);
		return panelist;
	}

	// Update panelist information
	public synchronized void updatePanelist(Panelist panelist) {
		panelistRepository.save(panelist);
	}

	// Get team details by user ID and hackathon ID
	public List<TeamDetailsToPanelistDTO> getTeamDetailsByUserIdAndHackathonId(Integer hackathonId, Integer userId) {
		User user = userService.getUser(userId);
		Panelist panelist = null;
		for (Panelist p : user.getPanelists()) {
			if (p.getHackathon().getHackathonId() == hackathonId) {
				panelist = p;
				break;
			}
		}
		LocalDateTime currentTime = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
		if (panelist == null) {
			throw new UserNotFoundException("Panelist not found");
		}
		if (isDevelopment || currentTime.isAfter(panelist.getHackathon().getIdeaSubmissionDeadline())
				&& currentTime.isBefore(panelist.getHackathon().getShortListDeadLine())) {
			if (panelist.getHackathon().getHackathonId().equals(hackathonId)) {
				List<TeamDetailsToPanelistDTO> teamDetailsToPanelistDTOs = panelist.getTeam().stream().map(team -> {
					TeamDetailsToPanelistDTO teamDTO = new TeamDetailsToPanelistDTO();
					teamDTO.setTeamId(team.getTeamId());
					teamDTO.setIdeaBody(team.getIdeaBody());
					teamDTO.setIdeaDomain(team.getIdeaDomain());
					teamDTO.setIdeaTitle(team.getIdeaTitle());
					teamDTO.setTeamName(team.getName());
					teamDTO.setStatus(team.getStatus());
					return teamDTO;
				}).collect(Collectors.toList());
				if (teamDetailsToPanelistDTOs.size() == 0) {
					throw new TeamNotFoundException("No team assigned");
				} else {
					return teamDetailsToPanelistDTOs;
				}

			} else {
				throw new HackathonNotExistsException("hackathon not found");
			}
		} else if (currentTime.isAfter(panelist.getHackathon().getShortListDeadLine())) {
			throw new UnauthorizedException("Idea reviewing is no longer allowed");
		} else {
			throw new UnauthorizedException("Idea reviewing is not started");
		}

	}

	// Get panelist hackathon DTO
	public PanelistHackathonDTO getPanelistHackathonDTO(int panelistId) {
		return panelistRepository.findAssignedHackathon(panelistId);
	}
}
