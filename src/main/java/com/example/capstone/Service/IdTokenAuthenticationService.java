package com.example.capstone.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.capstone.DTO.TokenClaimDTO;
import com.example.capstone.Exceptions.ErrorMapper;
import com.example.capstone.Exceptions.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class IdTokenAuthenticationService {
	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String clineId;
	public TokenClaimDTO verifyToken(String idTokenString) {
	    NetHttpTransport transport = new NetHttpTransport();
	    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
	    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
	            .setAudience(Collections.singletonList(clineId))
	            .build();

	    try {
	        GoogleIdToken idToken = verifier.verify(idTokenString);
	        if (idToken != null) {
	            String email = (String) idToken.getPayload().get("email");
	            String name=(String) idToken.getPayload().get("name");
	            return new TokenClaimDTO(email,name);
	            // Extract other necessary information from the payload
	        } else {
	            // Invalid token
	            throw new UnauthorizedException("Token is not valid");
	            }
	    } catch ( GeneralSecurityException | IOException e) {
	    	throw new UnauthorizedException("token is not valid");
	    }
	}
}
