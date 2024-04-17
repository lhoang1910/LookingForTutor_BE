package com.ltf.tutorservice.dto.response;

import jakarta.persistence.Column;

public class UserProfile {
	private long id;
	private String fullName;
	@Column(name = "username", unique = true)
	private String username;
	private String address;
	@Column(name = "email", unique = true)
	private String email;
	private String number;

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfile(String fullName, String username, String address, String email, String number, long id) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.address = address;
		this.email = email;
		this.number = number;
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
