package com.ltf.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltf.userservice.dto.request.CreateAccountRequest;
import com.ltf.userservice.dto.request.ResetPasswordRequest;
import com.ltf.userservice.dto.request.SignInRequest;
import com.ltf.userservice.dto.request.VerifyRequest;
import com.ltf.userservice.dto.response.AccountResponse;
import com.ltf.userservice.service.AuthService;
import com.ltf.userservice.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public AccountResponse signUp(@RequestBody CreateAccountRequest createAccountRequest) {
		return authService.signUp(createAccountRequest);
	}
	
	@PutMapping("/verify")
	public AccountResponse verify(@RequestBody VerifyRequest verifyRequest) {
		return authService.verifyAccount(verifyRequest);
	}
	
	@PostMapping("/signin")
	public AccountResponse signIn(@RequestBody SignInRequest signInRequest) {
		return authService.signIn(signInRequest);
	}
	
	@PostMapping("/send-token")
	public AccountResponse sendVerificationCode(@RequestParam("email") String email) {
		return authService.sendVerificationCode(email);
	}
	
	@PutMapping("/forget-password")
	public AccountResponse forgetPassword(@RequestBody ResetPasswordRequest request) {
		return authService.forgetPassword(request);
	}
	
}
