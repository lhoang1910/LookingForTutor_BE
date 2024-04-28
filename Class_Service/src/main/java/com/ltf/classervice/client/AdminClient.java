package com.ltf.classervice.client;

import com.ltf.classervice.dto.response.TutorProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ADMINSERVICE")
public interface AdminClient {
    @GetMapping("/api/class/tutor/{classId}")
    public TutorProfileResponse getTutorByClassId(@PathVariable long classId);
}
