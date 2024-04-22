package com.ltf.tutorservice.controller;

import com.ltf.tutorservice.entity.Tutor;
import com.ltf.tutorservice.service.TutorRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ltf.tutorservice.client.UserServiceClient;
import com.ltf.tutorservice.dto.request.AddTutorProfileRquest;
import com.ltf.tutorservice.dto.response.TutorProfileResponse;
import com.ltf.tutorservice.dto.response.TutorResponse;
import com.ltf.tutorservice.dto.response.UserProfile;
import com.ltf.tutorservice.service.TutorService;

import java.util.List;

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

	@Autowired
	UserServiceClient userServiceClient;

	@Autowired
	TutorService tutorService;

	@Autowired
	TutorRedisService tutorRedisService;
	
	@GetMapping("/")
	public String testApi() {
		return "Hello World";
	}
	
	@PostMapping("/profile/update")
	public ResponseEntity<TutorResponse> updateProfile(@RequestBody AddTutorProfileRquest addTutorProfileRquest, @RequestHeader("loggedInUser") String loggedInUser) {
		tutorRedisService.clear();;
		return ResponseEntity.ok(tutorService.updateTutorProfile(loggedInUser, addTutorProfileRquest));
	}	
	
	@PostMapping("/profile/img")
	public ResponseEntity<TutorResponse> updateImg(@RequestParam("cccd") MultipartFile cccd,
	                               @RequestParam("studentCard") MultipartFile studentCard,
	                               @RequestHeader("loggedInUser") String loggedInUser) {
	    return ResponseEntity.ok(tutorService.updateTutorImg(loggedInUser, cccd, studentCard));
	}

	@GetMapping("/profile")
	public ResponseEntity<TutorProfileResponse> getCurrentTutorProfile(@RequestHeader("loggedInUser") String loggedInUser) {
		return ResponseEntity.ok(tutorService.getCurrentTutor(loggedInUser));
	}

}
