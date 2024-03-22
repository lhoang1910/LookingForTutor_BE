package com.ltf.userservice.dto.response;

public class AccountResponse {
	private String tittle;
	private String description;
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AccountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountResponse(String tittle, String description) {
		super();
		this.tittle = tittle;
		this.description = description;
	}
	
	
}
