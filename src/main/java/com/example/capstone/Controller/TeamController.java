package com.example.capstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone.DTO.AddIdeaDTO;
import com.example.capstone.DTO.IdeaDetailsRequestDTO;
import com.example.capstone.DTO.MessageResponse;
import com.example.capstone.DTO.TeamCreationDTO;
import com.example.capstone.Exceptions.BadRequestException;
import com.example.capstone.Service.TeamService;

import jakarta.validation.Valid;

//TeamController handles the creation, updating teams for a hackathon.
//It also handles the submission of idea details and files for a team.
//The controller uses the TeamService to perform these operations

@RestController
@RequestMapping("Team")
public class TeamController {

	@Autowired
	private TeamService teamService;

	// Create a new team for a hackathon
	// The team is created by a user
	@PostMapping("{hackathonId}/{userId}")
	public ResponseEntity<MessageResponse> createTeam(@PathVariable int hackathonId, @PathVariable int userId,
			@RequestBody(required = false) @Valid TeamCreationDTO teamCreationDTO) {
		if (teamCreationDTO == null) {
			throw new BadRequestException("Request body should not be null");
		}
		teamService.CreateTeam(hackathonId, userId, teamCreationDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Team created successfully"));
	}

	// Update the team details by adding an idea
	// The user submitting the idea is identified by the userId
	// The hackathonId identifies the hackathon
	@PostMapping("idea/{hackathonId}/{userId}")
	public ResponseEntity<MessageResponse> updateTeamDetails(@PathVariable int hackathonId, @PathVariable int userId,
			@RequestBody(required = false) @Valid AddIdeaDTO teamUpdateDTO) {
		if (teamUpdateDTO == null) {
			throw new BadRequestException("Request body should not be null");
		}
		teamService.updateTeamDetails(teamUpdateDTO, userId, hackathonId);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Idea submitted successfully"));
	}

	@PostMapping("rejected/{teamId}")
	public ResponseEntity<MessageResponse> updateTeamStatus(@PathVariable int teamId) {
		teamService.rejectTeam(teamId);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Team rejected successfully"));
	}

	// Update the team status to selected.
	// The teamId identifies the team to be selected.
	@PutMapping("selected/{teamId}")
	public ResponseEntity<MessageResponse> updateSelectedTeamStatus(@PathVariable int teamId) {
		teamService.selectTeamForNextStep(teamId);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Team selected successfully"));

	}

	// Update the team details with the provided idea details, including files.
	@PostMapping("ideaFiles/{hackathonId}/{userId}")
	public ResponseEntity<MessageResponse> updateIdeaDetails(@PathVariable Integer hackathonId,
			@PathVariable Integer userId, @RequestBody(required = false) @Valid IdeaDetailsRequestDTO requestBody) {
		if (requestBody == null) {
			throw new BadRequestException("Request body should not be null");
		}
		String message = teamService.FileSubmission(hackathonId, userId, requestBody);
		return ResponseEntity.ok(new MessageResponse(message));
	}

}
