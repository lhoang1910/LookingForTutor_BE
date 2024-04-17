package com.ltf.classervice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltf.classervice.dto.request.CreateClassRequest;
import com.ltf.classervice.dto.response.ClassInfoResponse;
import com.ltf.classervice.dto.response.ClassResponse;
import com.ltf.classervice.entities.Class;
import com.ltf.classervice.service.ClassService;

@RestController
@RequestMapping("/api/class")
public class Controller {

	@Autowired
	ClassService service;

	@GetMapping("/{id}")
	public Class getClassById(@PathVariable long id) {
		return service.getClassById(id);
	}

	@PostMapping("/add/{id}")
	public ClassResponse get(@RequestBody CreateClassRequest request, @PathVariable long id) {
		return service.createClass(request, id);
	}

	@GetMapping("/hadnttutor")
	public List<Class> getUnapprovedClasses() {
		return service.getUnapprovedClasses();
	}

	@GetMapping("/hadtutor")
	public List<Class> getApprovedClasses() {
		return service.getApprovedClasses();
	}

	@GetMapping("/info/all")
	public List<ClassInfoResponse> getAllClassInfo() {
		return service.getAllClasses();
	}

	@GetMapping("/info/{id}")
	public ClassInfoResponse getClassInfoById(@PathVariable long id) {
		return service.getClassInfoById(id);
	}
	
	@GetMapping("/classes")
	public List<Class> getALlClass(){
		return service.classes();
	}

}
