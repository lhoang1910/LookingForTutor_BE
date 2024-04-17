package com.ltf.tutorservice.dto.request;

public class AddTutorProfileRquest {

	private String school;

	private String major;

	private String startTime;

	private String endTime;

	private boolean hadExp;

	private String expDescription;

	public AddTutorProfileRquest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddTutorProfileRquest(long id, long userId, String school, String major, String startTime, String endTime,
			boolean hadExp, String expDescription) {
		super();
		this.school = school;
		this.major = major;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hadExp = hadExp;
		this.expDescription = expDescription;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isHadExp() {
		return hadExp;
	}

	public void setHadExp(boolean hadExp) {
		this.hadExp = hadExp;
	}

	public String getExpDescription() {
		return expDescription;
	}

	public void setExpDescription(String expDescription) {
		this.expDescription = expDescription;
	}
}
