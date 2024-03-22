package com.ltf.userservice.service;

import com.ltf.userservice.dto.request.CreateAccountRequest;
import com.ltf.userservice.dto.request.SignInRequest;
import com.ltf.userservice.dto.request.VerifyRequest;
import com.ltf.userservice.dto.response.AccountResponse;

public interface AuthService {
	AccountResponse signUp(CreateAccountRequest createAccountRequest);
	AccountResponse verifyAccount(VerifyRequest verifyRequest);
	AccountResponse signIn(SignInRequest signInRequest);
	Long getCurrentUserId();
	}
