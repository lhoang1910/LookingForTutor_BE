package com.ltf.tutorservice.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfile {
	private long id;
	private String fullName;
	private String username;
	private String address;
	private String email;
	private String number;
	private String sex;
	private String dateOfBirth;

}
