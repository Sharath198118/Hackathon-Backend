package com.example.capstone.Exceptions;

@SuppressWarnings("serial")
public class InvalidOtpException extends BadRequestException {
	public InvalidOtpException(String msg) {
		super(msg);
	}
}
