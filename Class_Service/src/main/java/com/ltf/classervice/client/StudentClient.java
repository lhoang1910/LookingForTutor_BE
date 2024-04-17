package com.ltf.classervice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ltf.classervice.dto.response.StudentResponse;

@FeignClient(name = "STUDENTSERVICE") 
public interface StudentClient {
	@GetMapping("/api/student/{id}")
	StudentResponse getStudentInfo(@PathVariable long id);
}
