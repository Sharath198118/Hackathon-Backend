package com.example.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone.Entity.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, String> {

}
