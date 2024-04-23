package com.ltf.classervice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ltf.classervice.dto.request.CreateClassRequest;
import com.ltf.classervice.dto.response.ClassResponse;
import com.ltf.classervice.entities.Class;
import com.ltf.classervice.service.ClassService;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/class")
public class ClassController {

	@Autowired
	ClassService service;

	@PostMapping("/create")
	ResponseEntity<ClassResponse> createClass(CreateClassRequest request, @RequestHeader("loggedInUser") String loggedInUser){
		return ResponseEntity.ok(service.createClass(request, loggedInUser));
	}

	@PostMapping("/my-classes")
	ResponseEntity<List<Class>> currentStudentClasses(@RequestHeader("loggedInUser") String loggedInUser){
		return ResponseEntity.ok(service.currentStudentClasses(loggedInUser));
	}

	@PostMapping("/all")
	ResponseEntity<List<Class>> getClassesPaidWithoutTutor(){
		return ResponseEntity.ok(service.getClassesPaidWithoutTutor());
	}

	@PostMapping("/paid/{id}")
	ResponseEntity<String> paid(@PathVariable long id){
		return ResponseEntity.ok(service.paid(id));
	}

	@PostMapping("/add-tutor/{id}")
	public ResponseEntity<String> addTutor(@PathVariable long id){
		return ResponseEntity.ok(service.hadTutor(id));
	}
}
