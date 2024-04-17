package com.ltf.tutorservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Lob
	private byte[] cccdImage;

	@Lob
	private byte[] studentCardImage;
	
	@OneToOne
    @PrimaryKeyJoinColumn
    private Tutor tutor;

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Tutor getTutor() {
		return tutor;
	}


	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
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

	public Image(byte[] cccdImage, byte[] studentCardImage) {
		super();
		this.cccdImage = cccdImage;
		this.studentCardImage = studentCardImage;
	}

	
}
