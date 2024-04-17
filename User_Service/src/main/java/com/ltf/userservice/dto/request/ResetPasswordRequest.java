package com.ltf.userservice.dto.request;

public class ResetPasswordRequest {
	private String email;
	private String newPassWord;
	private String confirmNewPassword;
	private String oldPassword;
	private int token;

	public ResetPasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResetPasswordRequest(String email, String newPassWord, String confirmNewPassword, String oldPassword) {
		super();
		this.email = email;
		this.newPassWord = newPassWord;
		this.confirmNewPassword = confirmNewPassword;
		this.oldPassword = oldPassword;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

}
