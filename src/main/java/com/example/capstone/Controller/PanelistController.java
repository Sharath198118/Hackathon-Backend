package com.example.capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone.DTO.TeamDetailsToPanelistDTO;
import com.example.capstone.Service.PanelistService;

//Controller to handle requests related to panelists
@RestController
@RequestMapping("panelist")
public class PanelistController {
	@Autowired
	private PanelistService panelistService;

	// Get team names by panelist and hackathon
	@GetMapping("{hackathonId}/{userId}")
	public ResponseEntity<List<TeamDetailsToPanelistDTO>> getTeamNamesByPanelistAndHackathon(
			@PathVariable Integer hackathonId, @PathVariable Integer userId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(panelistService.getTeamDetailsByUserIdAndHackathonId(hackathonId, userId));
	}
}
