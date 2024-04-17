package com.ltf.classervice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "class")
public class Class {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String subject;

	private String grade;

	private double numberOfWeek;

	private String tutorSex;

	private String classTime;

	private String furtherDescription;

	private int admissionFee;

	private long tution;

	private String address;

	private long tutorId;

	private boolean hadTutor;

	private String startTime;

	private long studentId;

	public Class() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Class(long id, String subject, String grade, double numberOfWeek, String tutorSex, String classTime,
			String furtherDescription, int admissionFee, long tution, String address, long tutorId, boolean hadTutor,
			String startTime, long studentId) {
		super();
		this.id = id;
		this.subject = subject;
		this.grade = grade;
		this.numberOfWeek = numberOfWeek;
		this.tutorSex = tutorSex;
		this.classTime = classTime;
		this.furtherDescription = furtherDescription;
		this.admissionFee = admissionFee;
		this.tution = tution;
		this.address = address;
		this.tutorId = tutorId;
		this.hadTutor = hadTutor;
		this.startTime = startTime;
		this.studentId = studentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getTutorId() {
		return tutorId;
	}

	public void setTutorId(long tutorId) {
		this.tutorId = tutorId;
	}

	public boolean isHadTutor() {
		return hadTutor;
	}

	public void setHadTutor(boolean hadTutor) {
		this.hadTutor = hadTutor;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

}
