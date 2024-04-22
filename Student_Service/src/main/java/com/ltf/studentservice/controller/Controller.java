package com.ltf.studentservice.controller;

import java.util.List;

import com.ltf.studentservice.dto.response.StudentProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ltf.studentservice.dto.request.AddStudentRequest;
import com.ltf.studentservice.dto.response.StudentResponse;
import com.ltf.studentservice.entities.Student;
import com.ltf.studentservice.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class Controller {

	@Autowired
	StudentService studentService;

	@PostMapping("/profile/update")
	public ResponseEntity<StudentResponse> addStudent(@RequestBody AddStudentRequest request,  @RequestHeader("loggedInUser") String loggedInUser) {
		return ResponseEntity.ok(studentService.updateStudentProfile(loggedInUser, request));
	}

	@PostMapping("/profile")
	public ResponseEntity<StudentProfileResponse> currentStudentProfile(@RequestHeader("loggedInUser") String loggedInUser){
		return ResponseEntity.ok(studentService.getCurrentStudentResponse(loggedInUser));
	}

}
