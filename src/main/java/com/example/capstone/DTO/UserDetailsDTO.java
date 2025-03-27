package com.example.capstone.DTO;

import com.example.capstone.Entity.Role;

public class UserDetailsDTO {
	private Integer userId;
	private String name;
	private String email;
	private Role role;
	private boolean isAvailable;
	private Integer assignedHackathon;
    private String domain;

	public UserDetailsDTO(Integer userId, String name, String email, Role role, boolean isAvailable,
			Integer assignedHackathon, String domain) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.role = role;
		this.isAvailable = isAvailable;
		this.assignedHackathon = assignedHackathon;
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setAssignedHackathon(Integer assignedHackathon) {
		this.assignedHackathon = assignedHackathon;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getAssignedHackathon() {
		return assignedHackathon;
	}

	public void setAssignedHackathon(int assignedHackathon) {
		this.assignedHackathon = assignedHackathon;
	}

	public UserDetailsDTO() {

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
