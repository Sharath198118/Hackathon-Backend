package com.example.capstone.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.capstone.Exceptions.ErrorMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		ErrorMapper error = new ErrorMapper(HttpStatus.FORBIDDEN.value(), request.getRequestURI().toString(),
				"You are not authorized to access this resource");
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		String jsonResponse = objectMapper.writeValueAsString(error);
		writer.write(jsonResponse);
		writer.flush();
	}

}
