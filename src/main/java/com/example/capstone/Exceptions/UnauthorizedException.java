package com.example.capstone.Exceptions;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String msg) {
		super(msg);
	}
}
