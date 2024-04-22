package com.ltf.studentservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequest {
	private int old;
	private int grade;
	private String furtherDescription;
}
