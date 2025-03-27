package com.example.capstone.Configuration;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.capstone.Entity.User;

@SuppressWarnings("serial")
public class UserInfoUserDetails implements UserDetails {

	private String name;
	private String password;
	private GrantedAuthority authority;

	public UserInfoUserDetails(User user) {
		this.name = user.getEmail();
		this.password = user.getPassword();
		this.authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	// Other methods such as isAccountNonExpired(), isAccountNonLocked(),
	// isCredentialsNonExpired(), isEnabled()
	// remain the same
}
