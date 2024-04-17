package com.ltf.studentservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String fullName;
	private int old;
	private String sex;
	private int grade;
	private String furtherDescription;
	private String phoneNumber;
	private String email;
	private boolean isApproved;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(long id, String fullName, int old, String sex, int grade, String furtherDescription,
			boolean isApproved, String phoneNumber, String email) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.old = old;
		this.sex = sex;
		this.grade = grade;
		this.furtherDescription = furtherDescription;
		this.isApproved = isApproved;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getFurtherDescription() {
		return furtherDescription;
	}

	public void setFurtherDescription(String furtherDescription) {
		this.furtherDescription = furtherDescription;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

}