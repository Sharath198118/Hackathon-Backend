package com.example.capstone.Exceptions;

public class ErrorMapper {
	private int status;
	private String URL;
	private String message;

	public ErrorMapper(int status, String uRL, String message) {
		super();
		this.status = status;
		URL = uRL;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
