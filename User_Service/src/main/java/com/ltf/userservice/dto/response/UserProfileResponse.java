package com.ltf.userservice.dto.response;

import com.ltf.userservice.entities.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
	private long id;
	private String fullName;
	private String username;
	private String dateOfBirth;
	private String email;
	private String number;
	private String sex;
	private String role;
}
