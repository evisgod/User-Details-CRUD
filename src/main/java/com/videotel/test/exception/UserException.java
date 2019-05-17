package com.videotel.test.exception;

/**
 * UserException
 *
 */
public class UserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final boolean success;
	private final String error;

	public UserException(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	@Override
	public String getMessage() {
		String msg = super.getMessage();

		if (msg == null) {
			msg = getClass().getName();
		}
		return msg;
	}

	@Override
	public String toString() {
		return getMessage();
	}

	public boolean isSuccess() {
		return success;
	}

	public String getError() {
		return error;
	}
}