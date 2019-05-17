package com.videotel.test.domain;

import java.sql.Date;

/**
 * This is UserDetails JSON model corresponding to user details in DB.
 *
 */
public class UserDetails {
	private long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String country;
	private String nationality;

	public UserDetails() {

	}

	public UserDetails(long id, String firstName, String lastName, Date dateOfBirth, String country,
			String nationality) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
		this.nationality = nationality;
	}

	public long getId() {
		return id;
	}

	public void setId(long l) {
		this.id = l;
	}

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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "getUserResponse [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", country=" + country + ", nationality=" + nationality + "]";
	}
}