package com.ltf.adminservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TutorClassInfoResponse {
	private long classId;
	private String subject;
	private String grade;
	private double numberOfWeek;
	private String tutorSex;
	private String classTime;
	private String furtherDescription;
	private long fee;
	private String address;
	private String studentFullName;
	private int studentOld;
	private String studentSex;
	private int studentGrade;
	private long tutorId;
	private long userId;
	private String fullName;
	private String username;
	private String email;
	private String number;
	private String school;
	private String major;
	private String startTime;
	private String endTime;
	private boolean hadExp;
	private String expDescription;
	private int admissionFee;
	private long tution;
}
