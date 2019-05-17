package com.videotel.test.domain;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This is AddUserRequest JSON model to create a user.
 *
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUserRequest {
	@NotNull(message = "firstName is required field")
	@Size(min = 1, max = 60, message = "firstName cannot be longer than 60 characters")
	private String firstName;
	@NotNull(message = "lastName is required field")
	@Size(min = 1, max = 60, message = "lastName cannot be longer than 60 characters")
	private String lastName;
	@NotNull
	private Date dateOfBirth;
	@NotNull(message = "country is required field")
	@Size(min = 3, max = 3, message = "country cannot be longer than 3 characters")
	private String country;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AddUserRequest [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", country=" + country + "]";
	}
}
