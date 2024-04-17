package com.ltf.adminservice.dto.response;

public class ClassInfoResponse {
	private long classId;
	private String subject;
	private String grade;
	private double numberOfWeek;
	private String tutorSex;
	private String classTime;
	private String furtherDescription;
	private String address;
	private String studentFullName;
	private int studentOld;
	private String studentSex;
	private int studentGrade;
	private int admissionFee;
	private long tution;

	public ClassInfoResponse() {
		super();
		// TODO Auto-generated constructor stub
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

	public ClassInfoResponse(long classId, String subject, String grade, double numberOfWeek, String tutorSex,
			String classTime, String furtherDescription, String address, String studentFullName, int studentOld,
			String studentSex, int studentGrade, int admissionFee, long tution) {
		super();
		this.classId = classId;
		this.subject = subject;
		this.grade = grade;
		this.numberOfWeek = numberOfWeek;
		this.tutorSex = tutorSex;
		this.classTime = classTime;
		this.furtherDescription = furtherDescription;
		this.address = address;
		this.studentFullName = studentFullName;
		this.studentOld = studentOld;
		this.studentSex = studentSex;
		this.studentGrade = studentGrade;
		this.admissionFee = admissionFee;
		this.tution = tution;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
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

	public String getStudentFullName() {
		return studentFullName;
	}

	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}

	public int getStudentOld() {
		return studentOld;
	}

	public void setStudentOld(int studentOld) {
		this.studentOld = studentOld;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public int getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(int studentGrade) {
		this.studentGrade = studentGrade;
	}

}
