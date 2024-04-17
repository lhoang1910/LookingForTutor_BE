package com.ltf.tutorservice.service;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import com.ltf.tutorservice.dto.request.AddTutorProfileRquest;
import com.ltf.tutorservice.dto.response.TutorProfileResponse;
import com.ltf.tutorservice.dto.response.TutorResponse;

public interface TutorService {
	TutorResponse updateTutorProfile(@RequestHeader("loggedInUser") String loggedInUser, AddTutorProfileRquest addTutorProfileRquest);
	TutorResponse updateTutorImg(@RequestHeader("loggedInUser") String loggedInUser,MultipartFile cccd, MultipartFile studentCard);
	TutorProfileResponse getTutorInfo(long tutorId, String loggedInUser);
	TutorProfileResponse getTutorInfoById(long tutorId);
}
