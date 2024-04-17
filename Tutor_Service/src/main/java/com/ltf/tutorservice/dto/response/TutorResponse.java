package com.ltf.tutorservice.dto.response;

public class TutorResponse {
	private String tittle;
	private String message;
	public TutorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TutorResponse(String tittle, String message) {
		super();
		this.tittle = tittle;
		this.message = message;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
