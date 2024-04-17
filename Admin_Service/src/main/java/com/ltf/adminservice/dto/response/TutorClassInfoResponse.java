package com.ltf.adminservice.dto.response;

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
	private String userId;
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

	public TutorClassInfoResponse(long classId, String subject, String grade, double numberOfWeek, String tutorSex,
			String classTime, String furtherDescription, long fee, String address, String studentFullName,
			int studentOld, String studentSex, int studentGrade, long tutorId, String userId, String fullName,
			String username, String email, String number, String school, String major, String startTime, String endTime,
			boolean hadExp, String expDescription, int admissionFee, long tution) {
		super();
		this.classId = classId;
		this.subject = subject;
		this.grade = grade;
		this.numberOfWeek = numberOfWeek;
		this.tutorSex = tutorSex;
		this.classTime = classTime;
		this.furtherDescription = furtherDescription;
		this.fee = fee;
		this.address = address;
		this.studentFullName = studentFullName;
		this.studentOld = studentOld;
		this.studentSex = studentSex;
		this.studentGrade = studentGrade;
		this.tutorId = tutorId;
		this.userId = userId;
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.number = number;
		this.school = school;
		this.major = major;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hadExp = hadExp;
		this.expDescription = expDescription;
		this.admissionFee = admissionFee;
		this.tution = tution;
	}

	public TutorClassInfoResponse() {
		super();
		// TODO Auto-generated constructor stub
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

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
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
