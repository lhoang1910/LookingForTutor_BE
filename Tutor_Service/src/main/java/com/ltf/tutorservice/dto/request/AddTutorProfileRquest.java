package com.ltf.tutorservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddTutorProfileRquest {

	private String school;

	private String major;

	private String startTime;

	private String endTime;

	private boolean hadExp;

	private String expDescription;

}
