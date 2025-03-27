package com.example.capstone.Configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.example.capstone.DTO.MessageResponse;
import com.example.capstone.Exceptions.ErrorMapper;
import com.example.capstone.Service.TokenService;
import com.example.capstone.filter.JwtAuthFilter;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validator;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableAsync
public class SecurityConfig {
	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String clientId;

	@Value("${spring.security.oauth2.client.registration.google.client-secret}")
	private String clientSecret;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	AuthenticationSuccessHandler successHandler;

	@Bean
	public HandlerExceptionResolver handlerExceptionResolver() {
		return new DefaultHandlerExceptionResolver();
	}

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Autowired
	private TokenService tokenService;

	@Bean
	// authentication
	public UserDetailsService userDetailsService() {
		return new UserInfoUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthFilter jwtAuthFilter() {
		return new JwtAuthFilter(handlerExceptionResolver());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/User/user", "/User/ssoLogin", "/User/ssoRegister","/User/login", "/User/register", "/User/verifyOtp",
						"/User/forgotPassword", "/User/changePassword", "/User/logout", "/ContactDetails/Contact",
						"/Hackathon", "/Bot/recommend")
				.permitAll().requestMatchers("User/{id}")
				.hasAnyAuthority("ROLE_admin", "ROLE_judge", "ROLE_panelist", "ROLE_participant")
				.requestMatchers("/Admin/**").hasAuthority("ROLE_admin")
				.requestMatchers("Judge/review/{teamid}", "Judge/selectedTeams/{hackathonId}")
				.hasAuthority("ROLE_judge")
				.requestMatchers("panelist/{hackathonId}/{userId}", "Team/rejected/{teamId}", "Team/selected/{teamId}")
				.hasAuthority("ROLE_panelist")
				.requestMatchers("Team/{hackathonId}/{userId}", "Team/idea/{hackathonId}/{userId}",
						"Team/ideaFiles/{hackathonId}/{userId}", "User/Teams/{userId}")
				.hasAuthority("ROLE_participant").anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider()).exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler()).and()
				.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class).logout()
				.logoutUrl("/User/logout").logoutSuccessHandler((request, response, authentication) -> {
					String authHeader = request.getHeader("Authorization");
					String token = null;
					if (authHeader == null) {
						ErrorMapper errorMapper = new ErrorMapper(HttpStatus.FORBIDDEN.value(),
								request.getRequestURI().toString(),
								"Access forbidden. Please provide valid authentication credentials.");
						response.setStatus(errorMapper.getStatus());
						response.setContentType("application/json");
						response.getWriter().write(objectMapper.writeValueAsString(errorMapper));
						return;
					}
					if (authHeader != null && authHeader.startsWith("Bearer ")) {
						token = authHeader.substring(7);
					}
					tokenService.blackListToken(token);
					MessageResponse messageResponse = new MessageResponse("Logout successful");
					response.setStatus(HttpStatus.OK.value());
					response.setContentType("application/json");
					response.getWriter().write(objectMapper.writeValueAsString(messageResponse));
				}).and().build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	private static class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
		public CustomAuthenticationSuccessHandler(String defaultTargetUrl) {
			setDefaultTargetUrl(defaultTargetUrl);
		}

		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws ServletException, IOException {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
