package com.example.capstone.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capstone.DTO.ContactDetailsDTO;
import com.example.capstone.Entity.ContactDetails;
import com.example.capstone.Exceptions.ResourceNotFoundException;
import com.example.capstone.Repository.ContactDetailsRepository;

import jakarta.transaction.Transactional;

@Service
public class ContactDetailsService {
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;

	@Transactional
	public void saveContactDetails(ContactDetailsDTO contactDetailsDTO) {
		ContactDetails conDetails = new ContactDetails();
		conDetails.setDetails(contactDetailsDTO.getDetails());
		conDetails.setEmail(contactDetailsDTO.getEmail());
		conDetails.setName(contactDetailsDTO.getName());
		contactDetailsRepository.save(conDetails);
	}

	public List<ContactDetailsDTO> getContactDetails() {
		List<ContactDetails> details = contactDetailsRepository.findAll();
		if (details.size() == 0) {
			throw new ResourceNotFoundException("No contacts found");
		} else {
			return details.stream()
					.map(detail -> new ContactDetailsDTO(detail.getName(), detail.getEmail(), detail.getDetails()))
					.collect(Collectors.toList());
		}
	}
}
