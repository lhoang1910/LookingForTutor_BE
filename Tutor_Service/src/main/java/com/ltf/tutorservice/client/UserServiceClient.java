package com.ltf.tutorservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ltf.tutorservice.dto.response.UserProfile;

@FeignClient(name = "USERSERVICE")
public interface UserServiceClient {

	@GetMapping("/api/user/current")
	UserProfile getCurrentUser(@RequestHeader("loggedInUser") String username);

	@GetMapping("/api/user/{id}")
	UserProfile getUserInfoById(@PathVariable long id);
}
