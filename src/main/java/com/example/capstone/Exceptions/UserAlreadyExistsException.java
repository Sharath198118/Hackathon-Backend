package com.example.capstone.Exceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends ConflictException {
	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
}
