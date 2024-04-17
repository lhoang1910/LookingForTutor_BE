package com.ltf.tutorservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CLASSSERVICE") 
public interface ClassServiceClient {
	@GetMapping("/api/class/hadtutor")
	List<Class> getApprovedClasses();
}
