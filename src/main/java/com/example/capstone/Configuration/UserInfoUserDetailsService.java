package com.example.capstone.Configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.capstone.Entity.User;
import com.example.capstone.Repository.UserRepository;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userInfo = repository.findByEmail(username);
		if (userInfo.isPresent()) {
			UserInfoUserDetails userDetails = new UserInfoUserDetails(userInfo.get());
			return userDetails;
		} else
			throw new UsernameNotFoundException("User not found" + username);
	}
}
