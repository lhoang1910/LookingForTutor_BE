package com.ltf.classervice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClassRequest {
	private String subject;
	private String grade;
	private double numberOfWeek;
	private double hoursPerSession;
	private String tutorSex;
	private String furtherDescription;
	private int admissionFee;
	private long tution;
	private String address;
	private String startTime;
	private long studentId;
	private boolean isApprove;

}
