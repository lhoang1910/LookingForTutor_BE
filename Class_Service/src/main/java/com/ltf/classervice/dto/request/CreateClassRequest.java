package com.ltf.classervice.dto.request;

public class CreateClassRequest {
	private String subject;
	private String grade;
	private double numberOfWeek;
	private String tutorSex;
	private String classTime;
	private String furtherDescription;
	private String address;
	private int admissionFee;
	private long tution;

	public CreateClassRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateClassRequest(String subject, String grade, double numberOfWeek, String tutorSex, String classTime,
			String furtherDescription, String address, int admissionFee, long tution) {
		super();
		this.subject = subject;
		this.grade = grade;
		this.numberOfWeek = numberOfWeek;
		this.tutorSex = tutorSex;
		this.classTime = classTime;
		this.furtherDescription = furtherDescription;
		this.address = address;
		this.admissionFee = admissionFee;
		this.tution = tution;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public double getNumberOfWeek() {
		return numberOfWeek;
	}

	public void setNumberOfWeek(double numberOfWeek) {
		this.numberOfWeek = numberOfWeek;
	}

	public String getTutorSex() {
		return tutorSex;
	}

	public void setTutorSex(String tutorSex) {
		this.tutorSex = tutorSex;
	}

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}

	public String getFurtherDescription() {
		return furtherDescription;
	}

	public void setFurtherDescription(String furtherDescription) {
		this.furtherDescription = furtherDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAdmissionFee() {
		return admissionFee;
	}

	public void setAdmissionFee(int admissionFee) {
		this.admissionFee = admissionFee;
	}

	public long getTution() {
		return tution;
	}

	public void setTution(long tution) {
		this.tution = tution;
	}

}
