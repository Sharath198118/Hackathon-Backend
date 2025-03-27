package com.example.capstone.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.capstone.DTO.JudgeHackathonDTO;
import com.example.capstone.Entity.Judge;
import com.example.capstone.Entity.Team;

@Repository
public interface JudgeRepository extends JpaRepository<Judge, Integer> {
	@Query("SELECT j.hackathon.teams FROM Judge j WHERE j.hackathon.hackathonId = :hackathonId")
    List<Team> findTeams(int hackathonId);
	@Query("SELECT NEW com.example.capstone.DTO.JudgeHackathonDTO(j.hackathon.hackathonId,j.hackathon.name,j.hackathon.reviewStartTime,j.hackathon.reviewEndTime,j.hackathon.judging_criteria) FROM Judge j WHERE j.judgeId=:judgeId AND j.hackathon.hackathonStatus=started")
	JudgeHackathonDTO findAssignedHackathon(int judgeId);

}
