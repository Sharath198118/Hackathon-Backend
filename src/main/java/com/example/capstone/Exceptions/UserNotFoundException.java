package com.example.capstone.Exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends ResourceNotFoundException {

	public UserNotFoundException(String msg) {
		super(msg);
	}

}
