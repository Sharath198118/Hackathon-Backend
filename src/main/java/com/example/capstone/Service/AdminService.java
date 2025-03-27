package com.example.capstone.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capstone.DTO.RegisterEvaluatorDTO;
import com.example.capstone.Entity.Role;
import com.example.capstone.DTO.AddEvaluatorsDTO;
import com.example.capstone.DTO.AdminHackathonDTO;
import com.example.capstone.DTO.CreateHackathonDTO;
import com.example.capstone.DTO.GetEvaluatorsDTO;

//Service layer for admin related operations.
@Service
public class AdminService {

	// Autowired repositories and services to interact with the database and other
	// services
	@Autowired
	private HackathonService hackathonService;

	@Autowired
	private UserService userService;

	/**
	 * Create a new hackathon with the provided details.
	 * 
	 * @param createHackathonDTO Data transfer object containing hackathon creation
	 *                           details.
	 */
	public void createHackathon(CreateHackathonDTO createHackathonDTO) {
		hackathonService.CreateHackathon(createHackathonDTO);
	}

	/**
	 * Register a new evaluator.
	 * 
	 * @param addEvaluatorDTO Data transfer object containing evaluator registration
	 *                        details.
	 */
	public void addEvaluator(RegisterEvaluatorDTO addEvaluatorDTO) {
		userService.addEvaluator(addEvaluatorDTO);
	}

	/**
	 * Assign evaluators to a hackathon.
	 * 
	 * @param addEvaluatorsDTO Data transfer object containing evaluator assignment
	 *                         details.
	 */
	public void assignEvaluators(AddEvaluatorsDTO addEvaluatorsDTO) {
		hackathonService.addEvaluators(addEvaluatorsDTO);
	}

	/**
	 * Retrieve a list of all hackathons.
	 * 
	 * @return List of HackathonDTO containing hackathon data.
	 */
	public List<AdminHackathonDTO> getAllHackathons() {
		return hackathonService.getHackathonsForAdmin();
	}

	/**
	 * Get a list of available evaluators.
	 * 
	 * @return List of GetEvaluatorsDTO containing evaluator data.
	 */
	public List<GetEvaluatorsDTO> getEvaluators() {
		List<Role> roles = Arrays.asList(Role.judge, Role.panelist);
		return userService.getAvailableEvaluators(roles);
	}

	/**
	 * End the hackathon with the given ID.
	 * 
	 * @param hackathonId The ID of the hackathon to end.
	 */
	public void endHackathon(int hackathonId) {
		hackathonService.endHackathon(hackathonId);
	}
	
//	public void startHackathon(int hackathonId)
//	{
//		hackathonService.StartHackathon(hackathonId);
//	}

}
