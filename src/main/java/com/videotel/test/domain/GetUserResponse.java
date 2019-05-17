package com.videotel.test.domain;

import java.util.List;

/**
 * This is GetUserResponse JSON model to get user details.
 *
 */
public class GetUserResponse {
	private List<UserDetails> userDetails;

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public String toString() {
		return "getUserResponse [userDetails=" + userDetails + "]";
	}
}
