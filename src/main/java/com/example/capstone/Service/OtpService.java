package com.example.capstone.Service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.capstone.DTO.UserDTO;
import com.example.capstone.Entity.OtpEntity;
import com.example.capstone.Exceptions.InvalidOtpException;
import com.example.capstone.Exceptions.InvalidUserException;
import com.example.capstone.Exceptions.UnauthorizedException;
import com.example.capstone.Repository.OtpRepository;

@Service
public class OtpService {
	@Autowired
	private OtpRepository otpRepository;
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String generateOTP() {
		int max = 999999;
		int min = 100000;
		Random random = new Random();
		Integer otp = random.nextInt(max - min + 1) + min;
		return String.valueOf(otp);
	}

	public void saveOtp(String email, String name, String password, String otp) {
		otpRepository.save(new OtpEntity(email, name, password, otp));
	}

	public UserDTO verifyOtp(String email, String otp) throws InvalidOtpException, InvalidUserException {
		Optional<OtpEntity> otpEntity = otpRepository.findById(email);
		if (otpEntity.isPresent()) {
			if (passwordEncoder.matches(otp, otpEntity.get().getOtp())) {
				otpRepository.deleteById(email);
				return new UserDTO(otpEntity.get().getEmail(), otpEntity.get().getName(),
						otpEntity.get().getPassword());
			} else {
				throw new InvalidOtpException("Otp is not valid");
			}
		} else {
			throw new InvalidUserException("User is not valid");
		}
	}

	public String getOTP(String email) {
		Optional<OtpEntity> otpEntity = otpRepository.findById(email);
		if (otpEntity.isPresent()) {
			return otpEntity.get().getOtp();
		} else {
			throw new UnauthorizedException("Invalid email");
		}
	}

	public void deleteById(String email) {
		otpRepository.deleteById(email);
	}
}
