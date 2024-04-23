package com.ltf.adminservice.controller;

import java.util.List;

import com.ltf.adminservice.entities.ClassManagerment;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltf.adminservice.dto.response.AdminResponse;
import com.ltf.adminservice.dto.response.TutorClassInfoResponse;
import com.ltf.adminservice.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService service;

	@GetMapping("/class-tutor/unapproved")
	public ResponseEntity<List<ClassManagerment>> getAllUnapprovedClasses() {
		return ResponseEntity.ok(service.getAllUnapproved());
	}

	@GetMapping("/class-tutor/approved")
	public ResponseEntity<List<ClassManagerment>> getAllApprovedClasses() {
		return ResponseEntity.ok(service.getAllUnapproved());
	}

	@PutMapping("/approve-request/{id}")
	public ResponseEntity<AdminResponse> approveRequest(@PathVariable long id) {
		return ResponseEntity.ok(service.approveTutorWithClass(id));
	}

	@GetMapping("/class-tutor/paid")
	public ResponseEntity<List<ClassManagerment>> getAllPaid(){
		return ResponseEntity.ok(service.getALlPaid());
	}

	@GetMapping("/class-tutor/unpaid")
	public ResponseEntity<List<ClassManagerment>> getAllUnPaid(){
		return ResponseEntity.ok(service.getAllUnPaid());
	}

	@GetMapping("/class-tutor/{id}")
	public ResponseEntity<ClassManagerment> getById(@PathVariable long id){
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("/class-tutor/tutor/{id}")
	public ResponseEntity<List<ClassManagerment>> getByTutorId(@PathVariable long id){
		return ResponseEntity.ok(service.getAllByTutorId(id));
	}

	@GetMapping("/class-tutor/class/{id}")
	public ResponseEntity<ClassManagerment> getByClassId(@PathVariable long id){
		return ResponseEntity.ok(service.getByClassId(id));
	}

	@GetMapping("/class-tutor/all")
	public ResponseEntity<List<ClassManagerment>> getAllClassManagerment(){
		return ResponseEntity.ok((service.getAllCLassManagerment()));
	}
}
