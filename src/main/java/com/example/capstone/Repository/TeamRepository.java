package com.example.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.capstone.Entity.Team;

// Repository for managing teams in the database.
// This interface extends JpaRepository to provide basic CRUD operations for the Team entity.
// It can be used to find, save, delete, and update teams in the database.
// @param <Team> the Team entity class
// @param <Integer> the type of the primary key of the Team entity

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
