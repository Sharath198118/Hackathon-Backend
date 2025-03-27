package com.example.capstone.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.capstone.Exceptions.UnauthorizedException;

@Component
public class JwtService {

	public String generateToken(String userName, String Role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("String", Role);
		return CreateToken(claims, userName);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		if (!isTokenExpired(token)) {
			return username.equals(userDetails.getUsername());
		} else {
			return null;
		}
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private String CreateToken(Map<String, Object> claims, String userName) {
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private java.security.Key getSignKey() {
		byte[] keyByte = Decoders.BASE64.decode("g7lyhETgiSv9cOY8uWXLsXHI2V6KH87938rgGRy/EoU01f1DYdILks9gaca7cLcE");
		return Keys.hmacShaKeyFor(keyByte);
	}
}
