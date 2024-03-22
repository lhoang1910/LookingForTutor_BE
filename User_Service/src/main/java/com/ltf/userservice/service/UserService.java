package com.ltf.userservice.service;

import com.ltf.userservice.dto.response.UserProfileResponse;

public interface UserService {
	UserProfileResponse getUserById(long id);
	UserProfileResponse getCurrentUser();
}
