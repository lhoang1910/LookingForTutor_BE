package com.ltf.adminservice.dto.response;

import jakarta.persistence.Lob;

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
	@Lob
	private byte[] cccdImage;

	@Lob
	private byte[] studentCardImage;

	public TutorProfileResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public byte[] getCccdImage() {
		return cccdImage;
	}

	public void setCccdImage(byte[] cccdImage) {
		this.cccdImage = cccdImage;
	}

	public byte[] getStudentCardImage() {
		return studentCardImage;
	}

	public void setStudentCardImage(byte[] studentCardImage) {
		this.studentCardImage = studentCardImage;
	}

	public TutorProfileResponse(long tutorId, String userId, String fullName, String username, String address,
			String email, String number, String school, String major, String startTime, String endTime, boolean hadExp,
			String expDescription, byte[] cccdImage, byte[] studentCardImage) {
		super();
		this.tutorId = tutorId;
		this.userId = userId;
		this.fullName = fullName;
		this.username = username;
		this.address = address;
		this.email = email;
		this.number = number;
		this.school = school;
		this.major = major;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hadExp = hadExp;
		this.expDescription = expDescription;
		this.cccdImage = cccdImage;
		this.studentCardImage = studentCardImage;
	}

	public long getTutorId() {
		return tutorId;
	}

	public void setTutorId(long tutorId) {
		this.tutorId = tutorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
