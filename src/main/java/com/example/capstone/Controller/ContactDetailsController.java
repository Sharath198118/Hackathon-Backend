package com.example.capstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.capstone.DTO.ContactDetailsDTO;
import com.example.capstone.DTO.MessageResponse;
import com.example.capstone.Exceptions.UnauthorizedException;
import com.example.capstone.Service.ContactDetailsService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("ContactDetails")
public class ContactDetailsController {
	@Autowired
	private ContactDetailsService contactDetailsService;

	@PostMapping("Contact")
	public ResponseEntity<MessageResponse> addContact(
			@RequestBody(required = false) @Valid ContactDetailsDTO contactDetailsDTO) {
		if (contactDetailsDTO == null) {
			throw new UnauthorizedException("request body should not be null");
		}
		contactDetailsService.saveContactDetails(contactDetailsDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new MessageResponse("Saved contact successfully, We will be reaching you soon"));
	}
}
