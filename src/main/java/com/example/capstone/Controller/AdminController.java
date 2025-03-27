package com.example.capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone.DTO.RegisterEvaluatorDTO;
import com.example.capstone.Exceptions.BadRequestException;
import com.example.capstone.DTO.AddEvaluatorsDTO;
import com.example.capstone.DTO.AdminHackathonDTO;
import com.example.capstone.DTO.ContactDetailsDTO;
import com.example.capstone.DTO.CreateHackathonDTO;
import com.example.capstone.DTO.GetEvaluatorsDTO;
import com.example.capstone.DTO.MessageResponse;
import com.example.capstone.Service.AdminService;
import com.example.capstone.Service.ContactDetailsService;

import jakarta.validation.Valid;

//Controller for admin related API endpoints
@RestController
@RequestMapping("Admin")
public class AdminController {

	// Injecting the AdminService dependency
	@Autowired
	private AdminService adminService;
	@Autowired
	private ContactDetailsService contactDetailsService;

	// Endpoint to create a new hackathon
	@PostMapping("hackathon")
	public ResponseEntity<MessageResponse> createHackathon(
			@RequestBody(required = false) @Valid CreateHackathonDTO createHackathonDTO) {
		if (createHackathonDTO == null) {
			throw new BadRequestException("request body should not empty");
		}
		adminService.createHackathon(createHackathonDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Hackathon created successfully"));
	}

	// Endpoint to register a new evaluator
	@PostMapping("Evaluator")
	public ResponseEntity<MessageResponse> addEvaluator(
			@RequestBody(required = false) @Valid RegisterEvaluatorDTO addEvaluatorDTO) {
		if (addEvaluatorDTO == null) {
			throw new BadRequestException("request body should not empty");
		}
		adminService.addEvaluator(addEvaluatorDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Evaluator added successfully"));
	}

	// Endpoint to assign evaluators to a hackathon
	@PostMapping("assign")
	public ResponseEntity<MessageResponse> assignEvaluators(
			@RequestBody(required = false) @Valid AddEvaluatorsDTO addEvaluatorsDTO) {
		if (addEvaluatorsDTO == null) {
			throw new BadRequestException("request body should not empty");
		}
		adminService.assignEvaluators(addEvaluatorsDTO);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Evaluators assigned successfully"));
	}

//	 Endpoint to retrieve all hackathons
	@GetMapping("hackathon")
	public ResponseEntity<List<AdminHackathonDTO>> getHackathons() {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllHackathons());
	}

	// Endpoint to retrieve all evaluators
	@GetMapping("Evaluator")
	public ResponseEntity<List<GetEvaluatorsDTO>> getEvaluators() {
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getEvaluators());
	}

	// Endpoint to mark a hackathon as ended
	@PutMapping("hackathon/end/{hackathonid}")
	public ResponseEntity<MessageResponse> endHackathon(@PathVariable int hackathonid) {
		adminService.endHackathon(hackathonid);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Hackathon ended successfully"));
	}

	@GetMapping("contactDetails")
	public ResponseEntity<List<ContactDetailsDTO>> getContactDetails() {
		return ResponseEntity.status(HttpStatus.OK).body(contactDetailsService.getContactDetails());
	}

}
