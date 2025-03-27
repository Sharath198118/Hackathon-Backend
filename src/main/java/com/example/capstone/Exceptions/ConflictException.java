package com.example.capstone.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
	public ConflictException(String msg) {
		super(msg);
	}

}
