package com.ltf.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResetPasswordRequest {
	private String email;
	private String newPassWord;
	private String confirmNewPassword;
	private String oldPassword;
	private int token;

}
