package com.example.capstone.DTO;

import com.example.capstone.Entity.Role;

public class GetUserDTO {
	private Integer userId;
	private String name;
	private String email;
	private Role role;
	private boolean isAvailable;
	private Integer assignedHackathon;

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Integer getAssignedHackathon() {
		return assignedHackathon;
	}

	public void setAssignedHackathon(Integer assignedHackathon) {
		this.assignedHackathon = assignedHackathon;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

}
