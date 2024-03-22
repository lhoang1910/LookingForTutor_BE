package com.ltf.userservice.dto.response;

import jakarta.persistence.Column;

public class UserProfileResponse {
	private long id;
	private String fullName;
	@Column(name = "username", unique = true)
	private String username;
	private String address;
	@Column(name = "email", unique = true)
	private String email;
	private String number;

	public UserProfileResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfileResponse(String fullName, String username, String address, String email, String number, long id) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.address = address;
		this.email = email;
		this.number = number;
		this.id = id;
	}

}
