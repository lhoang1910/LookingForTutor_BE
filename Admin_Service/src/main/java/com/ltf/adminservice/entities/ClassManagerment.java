package com.ltf.adminservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "class_managerment")
public class ClassManagerment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long tutorId;

	private long classId;

	private boolean approved;

	private String timeApprove;

	private boolean paid;

	public ClassManagerment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassManagerment(long id, long tutorId, long classId, boolean approved, String timeApprove, boolean paid) {
		super();
		this.id = id;
		this.tutorId = tutorId;
		this.classId = classId;
		this.approved = approved;
		this.timeApprove = timeApprove;
		this.paid = paid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTutorId() {
		return tutorId;
	}

	public void setTutorId(long tutorId) {
		this.tutorId = tutorId;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getTimeApprove() {
		return timeApprove;
	}

	public void setTimeApprove(String timeApprove) {
		this.timeApprove = timeApprove;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	
}
