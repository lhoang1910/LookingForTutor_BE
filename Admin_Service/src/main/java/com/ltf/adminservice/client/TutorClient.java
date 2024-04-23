package com.ltf.adminservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ltf.adminservice.dto.response.TutorProfileResponse;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TUTORSERVICE")
public interface TutorClient {
	@GetMapping("/api/tutor/profile/{id}")
	TutorProfileResponse tutorProfileResponse(@PathVariable long id);

	@GetMapping("/api/tutor/profile")
	TutorProfileResponse getCurrentTutorProfile(@RequestHeader("loggedInUser") String loggedInUser);
}
