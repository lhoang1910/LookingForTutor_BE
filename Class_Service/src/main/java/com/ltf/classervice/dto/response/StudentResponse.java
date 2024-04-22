package com.ltf.classervice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
	private long userId;
	private long studentId;
	private String fullName;
	private String username;
	private String address;
	private String email;
	private String number;
	private int old;
	private int grade;
	private String furtherDescription;
	private String sex;
	private String dateOfBirth;

}
