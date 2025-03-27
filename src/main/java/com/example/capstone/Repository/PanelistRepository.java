package com.example.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.capstone.DTO.PanelistHackathonDTO;
import com.example.capstone.Entity.Panelist;

//Repository for panelists
@Repository
public interface PanelistRepository extends JpaRepository<Panelist, Integer> {

	// Find assigned hackathon for a panelist
	@Query("SELECT NEW com.example.capstone.DTO.PanelistHackathonDTO(p.hackathon.hackathonId,p.hackathon.name,p.hackathon.ideaSubmissionDeadline,p.hackathon.shortListDeadLine) FROM Panelist p WHERE p.panelistId=:panelistId AND p.hackathon.hackathonStatus=started")
	PanelistHackathonDTO findAssignedHackathon(int panelistId);
}
