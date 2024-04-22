package com.ltf.classervice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ltf.classervice.dto.response.StudentResponse;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "STUDENTSERVICE") 
public interface StudentClient {
	@GetMapping("/api/student/profile")
	StudentResponse currentStudentProfile(@RequestHeader("loggedInUser") String loggedInUser);

	@GetMapping("/api/admin/student/{id}")
	StudentResponse getStudent(@PathVariable long id);
}
