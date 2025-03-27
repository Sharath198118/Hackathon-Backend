package com.example.capstone.DTO;

import com.example.capstone.Entity.Role;

public class GetEvaluatorsDTO {

	private int userId;
	private String name;
	private String email;
	private Enum<Role> role;
	private boolean isAvailable;
	private Integer assignedHackathon;
	private String domain;

	public GetEvaluatorsDTO(int userId, String name, String email, Enum<Role> role, boolean isAvailable,
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

	public GetEvaluatorsDTO() {

	}

	public GetEvaluatorsDTO(int userId, String email, String name, Enum<Role> role2) {
		super();
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.role = role2;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Enum<Role> getRole() {
		return role;
	}

	public void setRole(Enum<Role> role) {
		this.role = role;
	}

}
