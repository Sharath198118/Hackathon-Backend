package com.example.capstone.Controller;

import org.springframework.http.HttpHeaders;

import java.util.List;

import com.example.capstone.Exceptions.BadRequestException;
import com.example.capstone.Exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone.DTO.ChangePasswordDTO;
import com.example.capstone.DTO.EmailDTO;
import com.example.capstone.DTO.EmailVerificationDTO;
import com.example.capstone.DTO.IdTokenDTO;
import com.example.capstone.DTO.MessageResponse;
import com.example.capstone.DTO.TeamDetailsDTO;
import com.example.capstone.DTO.TokenClaimDTO;
import com.example.capstone.DTO.UserDTO;
import com.example.capstone.DTO.UserDetailsDTO;
import com.example.capstone.DTO.UserLoginDTO;
import com.example.capstone.Exceptions.UserNotFoundException;
import com.example.capstone.Service.IdTokenAuthenticationService;
import com.example.capstone.Service.JwtService;
import com.example.capstone.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("User")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
	private IdTokenAuthenticationService authenticationService;
	@PostMapping("register")
	public ResponseEntity<MessageResponse> generateOtp(@RequestBody(required=false) @Valid UserDTO userDto) {
		if(userDto==null)
		{
			throw new BadRequestException("request body should not be null");
		}
		userService.generateOTP(userDto);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("OTP generated successfully"));
	}

	@PostMapping("verifyOtp")
	public ResponseEntity<?> validateOtp(@RequestBody(required=false) @Valid EmailVerificationDTO emailVerificationDto) {
		if(emailVerificationDto==null)
		{
			throw new BadRequestException("request body should not be null");
		}
		userService.validateOTP(emailVerificationDto.getEmail(), emailVerificationDto.getOtp());
		UserDetailsDTO userDetails = userService.findUserByMail(emailVerificationDto.getEmail());
		String jwtToken = jwtService.generateToken(emailVerificationDto.getEmail(), userDetails.getRole().name());
		String tokenWithPrefix = "Bearer " + jwtToken;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", tokenWithPrefix);
		return ResponseEntity.ok().headers(headers).body(userDetails);
	}

	@PostMapping("login")
	public ResponseEntity<?> verifyUser(@RequestBody(required=false) @Valid UserLoginDTO userLoginDto) {
		if(userLoginDto==null)
		{
			throw new BadRequestException("all fields should present");
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
		if (authentication.isAuthenticated()) {
			UserDetailsDTO userDetails = userService.findUserByMail(userLoginDto.getEmail());
			String jwtToken = jwtService.generateToken(userLoginDto.getEmail(), userDetails.getRole().name());
			String tokenWithPrefix = "Bearer " + jwtToken;

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", tokenWithPrefix);

			return ResponseEntity.ok().headers(headers).body(userDetails);
		} else {
			throw new UserNotFoundException("User not found exception");
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDetailsDTO> getUser(@PathVariable int id) {
		System.out.println("hello");
		return ResponseEntity.status(HttpStatus.OK).body(userService.returnUserDetails(id));
	}

	@PostMapping("forgotPassword")
	public ResponseEntity<MessageResponse> forgotPassword(@RequestBody(required=false) @Valid EmailDTO email) {
		if(email==null)
		{
			throw new BadRequestException("request body should not empty");
		}
		userService.generateOTP(email.getEmail());
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("OTP sent successfully"));	
	}

	@PostMapping("changePassword")
	public ResponseEntity<MessageResponse> changePassword(@RequestBody(required=false) @Valid ChangePasswordDTO changePasswordDTO) {
		if(changePasswordDTO==null)
		{
			throw new BadRequestException("request body should not empty");
		}
		userService.verifyUserAndChangePassword(changePasswordDTO);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Password change sucessful"));
	}

	@GetMapping("Teams/{userId}")
	public ResponseEntity<List<TeamDetailsDTO>> getTeamDetails(@PathVariable int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getTeamDetails(userId));
	}

	@PostMapping("logout")
	public ResponseEntity<MessageResponse> logOutUser(HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("logout successful"));
	}
	@PostMapping("ssoRegister")
	public ResponseEntity<?> ssoregister(@RequestBody IdTokenDTO idtoken) 
	{
		TokenClaimDTO claimDTO= authenticationService.verifyToken(idtoken.getIdtoken());
		UserDetailsDTO userDetails = userService.findUserByMailSSO(claimDTO.getEmail());
		if(userDetails==null)
		{
			userService.createUserSSO(claimDTO.getEmail(), claimDTO.getName());
			userDetails= userService.findUserByMailSSO(claimDTO.getEmail());
			String jwtToken = jwtService.generateToken(claimDTO.getEmail(), userDetails.getRole().name());
			String tokenWithPrefix = "Bearer " + jwtToken;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", tokenWithPrefix);
			return ResponseEntity.ok().headers(headers).body(userDetails);
		}
		String jwtToken = jwtService.generateToken(claimDTO.getEmail(), userDetails.getRole().name());
		String tokenWithPrefix = "Bearer " + jwtToken;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", tokenWithPrefix);

		return ResponseEntity.ok().headers(headers).body(userDetails);
	}
	@PostMapping("ssoLogin")
	public ResponseEntity<?> ssoLogin(@RequestBody IdTokenDTO idtoken)
	{
		TokenClaimDTO claimDTO= authenticationService.verifyToken(idtoken.getIdtoken());
		UserDetailsDTO userDetails = userService.findUserByMailSSO(claimDTO.getEmail());
		if(userDetails==null)
		{
			throw new ResourceNotFoundException("User not found");
		}
		String jwtToken = jwtService.generateToken(claimDTO.getEmail(), userDetails.getRole().name());
		String tokenWithPrefix = "Bearer " + jwtToken;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", tokenWithPrefix);
		return ResponseEntity.ok().headers(headers).body(userDetails);
		
	}
	
	
	
}
