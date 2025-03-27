package com.example.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone.Entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

}
