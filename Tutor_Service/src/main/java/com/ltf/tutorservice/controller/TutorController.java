package com.ltf.tutorservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

	@Autowired
	UserServiceClient userServiceClient;

	@Autowired
	TutorService tutorService;
	
	@GetMapping("/")
	public String testApi() {
		return "Thanh cong";
	}

	@GetMapping("/current")
	public UserProfile getCurrentUser(@RequestHeader("loggedInUser") String loggedInUser) {
		UserProfile userProfile = userServiceClient.getCurrentUser(loggedInUser);
		return userProfile;
	}
	
	@PostMapping("/update-profile")
	public TutorResponse updateProfile(@RequestBody AddTutorProfileRquest addTutorProfileRquest, @RequestHeader("loggedInUser") String loggedInUser) {
		return tutorService.updateTutorProfile(loggedInUser, addTutorProfileRquest);
	}	
	
	@PostMapping("/update-img")
	public TutorResponse updateImg(@RequestParam("cccd") MultipartFile cccd,
	                               @RequestParam("studentCard") MultipartFile studentCard,
	                               @RequestHeader("loggedInUser") String loggedInUser) {
	    return tutorService.updateTutorImg(loggedInUser, cccd, studentCard);
	}

	
	@GetMapping("/get-profile/{id}")
	public TutorProfileResponse tutorProfileResponse(@RequestParam("id") long id, @RequestHeader("loggedInUser") String loggedInUser) {
		return tutorService.getTutorInfo(id, loggedInUser);
	}
	
	@GetMapping("/profile/{id}")
	public TutorProfileResponse tutorProfileResponse(@PathVariable long id) {
		return tutorService.getTutorInfoById(id);
	}
}
