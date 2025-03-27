package com.example.capstone.Exceptions;

@SuppressWarnings("serial")
public class InvalidUserException extends UnauthorizedException {
	public InvalidUserException(String msg) {
		super(msg);
	}
}
