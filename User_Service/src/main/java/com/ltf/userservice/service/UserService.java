package com.ltf.userservice.service;

import com.ltf.userservice.dto.request.CreateAccountRequest;
import com.ltf.userservice.dto.request.ResetPasswordRequest;
import com.ltf.userservice.dto.response.AccountResponse;
import com.ltf.userservice.dto.response.UserProfileResponse;

public interface UserService {
	UserProfileResponse getCurrentUser(String username);

	AccountResponse updateProfile(CreateAccountRequest request);

	UserProfileResponse getUserById(long id);

	AccountResponse changePassword(String username, ResetPasswordRequest request);

}
