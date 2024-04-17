package com.ltf.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltf.userservice.dto.request.CreateAccountRequest;
import com.ltf.userservice.dto.request.ResetPasswordRequest;
import com.ltf.userservice.dto.response.AccountResponse;
import com.ltf.userservice.dto.response.UserProfileResponse;
import com.ltf.userservice.service.AuthService;
import com.ltf.userservice.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthService authService;

	
	@GetMapping("/current")
	public UserProfileResponse getCurrentUser(@RequestHeader("loggedInUser") String username) {
		return userService.getCurrentUser(username);
	}
	
	@PutMapping("/update-profile")
	public AccountResponse updateUserProfile(CreateAccountRequest accountRequest) {
		return userService.updateProfile(accountRequest);
	}
	
	 @GetMapping("/{id}")
	public UserProfileResponse getUserInfoById(@PathVariable long id) {
		return userService.getUserById(id);
	}
	 
	 @PutMapping("/change-password")
	 public AccountResponse changePassword(@RequestHeader("loggedInUser") String username, ResetPasswordRequest request){
		 return userService.changePassword(username, request);
	 }
	
}
