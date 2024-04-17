package com.ltf.classervice.dto.response;

public class ClassResponse {
	private String tittle;
	private String message;

	public ClassResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassResponse(String tittle, String message) {
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
