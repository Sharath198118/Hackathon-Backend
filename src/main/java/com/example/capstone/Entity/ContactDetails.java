package com.example.capstone.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class ContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contactDetailsId;
	private String name;
	private String email;
	@Lob
	@Column(length = 3000)
	private String details;

	public ContactDetails() {

	}

	public Integer getContactDetailsId() {
		return contactDetailsId;
	}

	public void setContactDetailsId(Integer contactDetailsId) {
		this.contactDetailsId = contactDetailsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
