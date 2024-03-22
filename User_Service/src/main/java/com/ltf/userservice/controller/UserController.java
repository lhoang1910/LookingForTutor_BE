package com.ltf.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltf.userservice.dto.response.UserProfileResponse;
import com.ltf.userservice.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public UserProfileResponse getMethodName(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/current")
	public UserProfileResponse getCurrentUser() {
		return userService.getCurrentUser();
	}
	
}
