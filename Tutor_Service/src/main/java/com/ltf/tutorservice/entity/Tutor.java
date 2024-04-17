package com.ltf.tutorservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutor")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long userId;

	public long getUserId() {
		return userId;
	}

	private String school;

	private String major;

	private String startTime;

	private String endTime;

	private boolean hadExp;

	private String expDescription;

	@OneToOne(mappedBy = "tutor")
	private Image image;

	public Tutor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public Tutor(long id, long userId, String school, String major, String startTime, String endTime, boolean hadExp,
			String expDescription) {
		super();
		this.id = id;
		this.userId = userId;
		this.school = school;
		this.major = major;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hadExp = hadExp;
		this.expDescription = expDescription;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
