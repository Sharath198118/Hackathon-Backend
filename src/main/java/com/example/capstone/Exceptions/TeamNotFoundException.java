package com.example.capstone.Exceptions;

@SuppressWarnings("serial")
public class TeamNotFoundException extends UserNotFoundException {
	public TeamNotFoundException(String msg) {
		super(msg);
	}
}
