package com.videotel.test.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final boolean success;
	private final String error;

	public UserNotFoundException(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getError() {
		return error;
	}
}