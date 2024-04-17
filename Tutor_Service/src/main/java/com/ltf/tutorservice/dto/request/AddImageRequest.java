package com.ltf.tutorservice.dto.request;

import jakarta.persistence.Lob;

public class AddImageRequest {
	@Lob
	private byte[] cccdImage;

	@Lob
	private byte[] studentCardImage;

	public AddImageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddImageRequest(byte[] cccdImage, byte[] studentCardImage) {
		super();
		this.cccdImage = cccdImage;
		this.studentCardImage = studentCardImage;
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

}
