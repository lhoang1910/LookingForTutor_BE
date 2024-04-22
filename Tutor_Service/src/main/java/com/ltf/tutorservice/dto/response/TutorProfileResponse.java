package com.ltf.tutorservice.dto.response;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TutorProfileResponse {
	private long tutorId;
	private String userId;
	private String fullName;
	private String username;
	private String address;
	private String email;
	private String number;
	private String school;
	private String major;
	private String startTime;
	private String endTime;
	private boolean hadExp;
	private String expDescription;
	private String dateOfBirth;
	private  String sex;
	@Lob
	private byte[] cccdImage;

	@Lob
	private byte[] studentCardImage;


}
