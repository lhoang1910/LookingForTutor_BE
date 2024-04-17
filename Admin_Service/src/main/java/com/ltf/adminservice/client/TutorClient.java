package com.ltf.adminservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ltf.adminservice.dto.response.TutorProfileResponse;

@FeignClient(name = "TUTORSERVICE")
public interface TutorClient {
	@GetMapping("/api/tutor/profile/{id}")
	public TutorProfileResponse tutorProfileResponse(@PathVariable long id);
}
