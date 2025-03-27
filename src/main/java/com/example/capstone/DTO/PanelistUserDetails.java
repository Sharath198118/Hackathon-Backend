package com.example.capstone.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.capstone.Entity.Role;

public class PanelistUserDetails {
	private Integer userId;
	private String name;
	private String email;
	private Role role;
	private List<PanelistHackathonDTO> panelistHackathonDTO = new ArrayList<>();

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

	public List<PanelistHackathonDTO> getPanelistHackathonDTO() {
		return panelistHackathonDTO;
	}

	public void setPanelistHackathonDTO(List<PanelistHackathonDTO> panelistHackathonDTO) {
		this.panelistHackathonDTO = panelistHackathonDTO;
	}

}
