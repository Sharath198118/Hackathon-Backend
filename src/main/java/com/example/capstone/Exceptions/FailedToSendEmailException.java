package com.example.capstone.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedToSendEmailException extends RuntimeException {
	public FailedToSendEmailException(String msg) {
		super(msg);
	}
}
