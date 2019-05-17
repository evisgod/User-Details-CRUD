package com.videotel.test.exception;

/**
 * ErrorResponse : Error Response Body
 * 
 */
public class ErrorResponse {

	private boolean success;
	private String error;

	public ErrorResponse(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorResponse [success=" + success + ", error=" + error + "]";
	}
}