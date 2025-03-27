package com.example.capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone.DTO.HackathonDTO;
import com.example.capstone.Service.HackathonService;

//Controller for Hackathon related API endpoints
@RestController
@RequestMapping("Hackathon")
public class HackathonController {

	// Injecting the HackathonService dependency
	@Autowired
	private HackathonService hackathonService;

	// Endpoint to retrieve a list of all hackathons
	@GetMapping
	public ResponseEntity<List<HackathonDTO>> getHackathons() {
		return ResponseEntity.status(HttpStatus.OK).body(hackathonService.getAllHackathons());
	}
}
