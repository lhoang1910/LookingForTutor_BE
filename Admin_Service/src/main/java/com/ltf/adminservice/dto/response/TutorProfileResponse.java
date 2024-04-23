package com.ltf.adminservice.dto.response;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorProfileResponse {
	private long tutorId;
	private long userId;
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
	@Lob
	private byte[] cccdImage;

	@Lob
	private byte[] studentCardImage;
}
