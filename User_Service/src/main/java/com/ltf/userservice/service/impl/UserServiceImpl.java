package com.ltf.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ltf.userservice.dto.response.UserProfileResponse;
import com.ltf.userservice.entities.User;
import com.ltf.userservice.repository.UserRepository;
import com.ltf.userservice.service.UserService;

import jakarta.ws.rs.NotFoundException;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;

	@Override
	public UserProfileResponse getUserById(long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Ten dang nhap khong ton tai"));
		UserProfileResponse profileResponse = new UserProfileResponse(user.getFullName(), user.getUsername(),
																	user.getAddress(), user.getEmail(), user.getNumber(), user.getId());
		return profileResponse;
	}

	@Override
	public UserProfileResponse getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByUsername(currentUserName).orElseThrow(() -> new NotFoundException("Tai khoan dang nhap khong ton tai"));
        UserProfileResponse userProfileResponse = new UserProfileResponse(user.getFullName(), user.getUsername(),
				user.getAddress(), user.getEmail(), user.getNumber(), user.getId());
       return userProfileResponse;        
	}
	
	
}