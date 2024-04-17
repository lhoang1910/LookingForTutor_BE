package com.ltf.tutorservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ltf.tutorservice.dto.response.TutorResponse;


@FeignClient(name = "ADMINSERVICE")
public interface AdminClient {
	@PostMapping("/api/admin/register-class/{tutorId}/{classId}")
	TutorResponse registerClass(@PathVariable long tutorId, @PathVariable long classId);
}
