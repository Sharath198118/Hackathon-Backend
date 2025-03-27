package com.example.capstone.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.capstone.Configuration.UserInfoUserDetailsService;
import com.example.capstone.Exceptions.ErrorMapper;
import com.example.capstone.Exceptions.UnauthorizedException;
import com.example.capstone.Service.JwtService;
import com.example.capstone.Service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;

	private HandlerExceptionResolver handlerExceptionResolver;

	@Autowired
	private UserInfoUserDetailsService userDetailsService;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private TokenService tokenService;

	public JwtAuthFilter(HandlerExceptionResolver handlerExceptionResolver) {
		this.handlerExceptionResolver = handlerExceptionResolver;
	}

	public boolean isAuthenticationNotRequired(String uri) {
		List<String> uris = Arrays.asList("/User/sso","/User/ssoRegister","/User/ssoLogin","/User/login", "/User/register", "/User/verifyOtp", "/User/forgotPassword",
				"/User/changePassword", "/User","/User/auth/google","/User/logout", "/ContactDetails/Contact", "/Hackathon");
		return uris.contains(uri);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isAuthenticationNotRequired(request.getRequestURI())) {
			
			filterChain.doFilter(request, response);
			return;
		}

		String authHeader = request.getHeader("Authorization");
		if (authHeader == null) {
			ErrorMapper errorMapper = new ErrorMapper(HttpStatus.FORBIDDEN.value(), request.getRequestURI().toString(),
					"Access forbidden. Please provide valid authentication credentials.");
			response.setStatus(errorMapper.getStatus());
			response.setContentType("application/json");
			response.getWriter().write(objectMapper.writeValueAsString(errorMapper));
			return;
		}
		String token = null;
		String username = null;

		try {
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7);
				username = jwtService.extractUsername(token);
			}
			if (tokenService.checkToken(token)) {
				throw new UnauthorizedException("Token is not valid try to re login");
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if (jwtService.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
			filterChain.doFilter(request, response);
		} catch (Exception ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		}
	}

}
