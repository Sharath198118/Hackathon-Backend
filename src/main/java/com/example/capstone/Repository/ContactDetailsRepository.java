package com.example.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone.Entity.ContactDetails;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Integer> {

}
