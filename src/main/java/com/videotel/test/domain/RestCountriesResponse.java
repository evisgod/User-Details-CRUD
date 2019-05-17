package com.videotel.test.domain;

/**
 * This is RestCountriesResponse JSON model to fetch country name.
 *
 */
public class RestCountriesResponse {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RestCountriesResponse [name=" + name + "]";
	}
}