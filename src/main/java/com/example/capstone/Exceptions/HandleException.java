package com.example.capstone.Exceptions;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandleException {
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ErrorMapper> handleError(ResourceNotFoundException e, HttpServletRequest request) {
		ErrorMapper error = new ErrorMapper(HttpStatus.NOT_FOUND.value(), request.getRequestURI().toString(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = { ConflictException.class })
	public ResponseEntity<ErrorMapper> handleConflictError(ConflictException e, HttpServletRequest request) {
		ErrorMapper error = new ErrorMapper(HttpStatus.CONFLICT.value(), request.getRequestURI().toString(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ErrorMapper> handleInternalServerError(Exception e, HttpServletRequest request) {
		if (e instanceof BadCredentialsException) {
			ErrorMapper error = new ErrorMapper(HttpStatus.BAD_REQUEST.value(), request.getRequestURI().toString(),
					"Invalid Credentials");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		} else if (e instanceof AccessDeniedException) {
			ErrorMapper error = new ErrorMapper(HttpStatus.UNAUTHORIZED.value(), request.getRequestURI().toString(),
					"Unauthorized");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		} else if (e instanceof SignatureException) {
			ErrorMapper error = new ErrorMapper(HttpStatus.UNAUTHORIZED.value(), request.getRequestURI().toString(),
					"Signature is not correct");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		} else if (e instanceof ExpiredJwtException) {
			ErrorMapper error = new ErrorMapper(HttpStatus.UNAUTHORIZED.value(), request.getRequestURI().toString(),
					"Token expired");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		} else {
			return null;
		}
	}
	@ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorMapper> handleHttpClientErrorException(HttpClientErrorException ex) {
        ErrorMapper error = new ErrorMapper(HttpStatus.FORBIDDEN.value(), "Access forbidden",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { BadRequestException.class })
	public ResponseEntity<ErrorMapper> handleInternalServerError(BadRequestException e, HttpServletRequest request) {
		ErrorMapper error = new ErrorMapper(HttpStatus.BAD_REQUEST.value(), request.getRequestURI().toString(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
			System.out.println(errorMessage);
		});
		return ResponseEntity.badRequest().body(errors);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = { UnauthorizedException.class })
	public ResponseEntity<ErrorMapper> handleInternalServerError(UnauthorizedException e, HttpServletRequest request) {
		ErrorMapper error = new ErrorMapper(HttpStatus.UNAUTHORIZED.value(), request.getRequestURI().toString(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

}
