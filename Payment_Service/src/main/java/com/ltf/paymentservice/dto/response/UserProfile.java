package com.ltf.paymentservice.dto.response;

import lombok.*;

import java.time.LocalDateTime;

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
	private String role;
}
