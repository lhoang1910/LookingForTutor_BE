package com.ltf.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltf.adminservice.dto.response.AdminResponse;
import com.ltf.adminservice.dto.response.ClassFee;
import com.ltf.adminservice.dto.response.TutorClassInfoResponse;
import com.ltf.adminservice.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService service;

	@GetMapping("/all-class-register")
	public List<TutorClassInfoResponse> getAllUnapprovedClasses() {
		return service.getAllUnapprovedClasses();
	}

	@PostMapping("/register-class/{tutorId}/{classId}")
	public AdminResponse registerClass(@PathVariable long tutorId, @PathVariable long classId) {
		return service.registerClass(tutorId, classId);
	}

	@PutMapping("/approve-request/{id}")
	public AdminResponse approveRequest(@PathVariable long id) {
		return service.approveTutorWithClass(id);
	}
	
	@GetMapping("/class-fee/{id}")
	ClassFee admissionClassFee(@PathVariable long id) {
		return service.admissionClassFee(id);
	}

}
