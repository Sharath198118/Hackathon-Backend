package com.example.capstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.capstone.DTO.AdminHackathonDTO;
import com.example.capstone.DTO.HackathonDTO;
import com.example.capstone.Entity.Hackathon;

public interface HackathonRepository extends JpaRepository<Hackathon, Integer> {
	@Query("SELECT new com.example.capstone.DTO.HackathonDTO(h.hackathonId,h.name,h.theme, h.startDate,h.ideaSubmissionDeadline,h.shortListDeadLine, h.implementationSubmissionDeadLine,h.reviewStartTime, h.reviewEndTime,h.description,h.prizes,h.rules,h.judging_criteria,h.hackathonStatus,h.firstTeamId,h.secondTeamId,h.thirdTeamId) FROM Hackathon h where h.hackathonStatus=started or h.hackathonStatus=ended")
	List<HackathonDTO> findHackathonsWithSelectedAttributes();
	@Query("SELECT new com.example.capstone.DTO.AdminHackathonDTO(h.hackathonId,h.name,h.theme, h.startDate,h.ideaSubmissionDeadline,h.shortListDeadLine, h.implementationSubmissionDeadLine,h.reviewStartTime, h.reviewEndTime,h.description,h.prizes,h.rules,h.judging_criteria,h.hackathonStatus,h.firstTeamId,h.secondTeamId,h.thirdTeamId,size(h.teams)) FROM Hackathon h")
	List<AdminHackathonDTO> findHackathonsForAdmin();
}
