package com.ltf.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<UserProfileResponse> getCurrentUser(@RequestHeader("loggedInUser") String username) {
		return ResponseEntity.ok(userService.getCurrentUser(username));
	}
	
	@PutMapping("/update-profile")
	public ResponseEntity<AccountResponse> updateUserProfile(CreateAccountRequest accountRequest) {
		return ResponseEntity.ok(userService.updateProfile(accountRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserProfileResponse> getUserInfoById(@PathVariable long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	 
	 @PutMapping("/change-password")
	 public ResponseEntity<AccountResponse> changePassword(@RequestHeader("loggedInUser") String username, ResetPasswordRequest request){
		 return ResponseEntity.ok(userService.changePassword(username, request));
	 }
	
}
