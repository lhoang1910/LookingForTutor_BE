package com.ltf.userservice.dto.request;

public class VerifyRequest {
	private String email;
	private int verifycationToken;
	public VerifyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VerifyRequest(String email, int verifycationToken) {
		super();
		this.email = email;
		this.verifycationToken = verifycationToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getVerifycationToken() {
		return verifycationToken;
	}
	public void setVerifycationToken(int verifycationToken) {
		this.verifycationToken = verifycationToken;
	}
	
	
}
