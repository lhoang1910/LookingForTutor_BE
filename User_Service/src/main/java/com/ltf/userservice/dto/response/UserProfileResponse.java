package com.ltf.userservice.dto.response;

import jakarta.persistence.Column;

public class UserProfileResponse {
	private long id;
	private String fullName;
	@Column(name = "username", unique = true)
	private String username;
	private String dateOfBirth;
	@Column(name = "email", unique = true)
	private String email;
	private String number;
	private String sex;

	public UserProfileResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfileResponse(long id, String fullName, String username, String dateOfBirth, String email,
			String number, String sex) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.number = number;
		this.sex = sex;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
