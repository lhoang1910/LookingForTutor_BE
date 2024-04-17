package com.ltf.studentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltf.studentservice.dto.request.AddStudentRequest;
import com.ltf.studentservice.dto.response.StudentResponse;
import com.ltf.studentservice.entities.Student;
import com.ltf.studentservice.service.StudentService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/student")
public class Controller {

	@Autowired
	StudentService studentService;

	@GetMapping("/all")
	public List<Student> getAllStudent() {
		return studentService.listStudent();
	}

	@GetMapping("/{id}")
	public Student getStudent(@PathVariable long id) {
		return studentService.getStudentById(id);
	}

	@PostMapping("/add")
	public StudentResponse addStudent(@RequestBody AddStudentRequest request) {
		return studentService.addStudent(request);
	}

	@PutMapping("/approve/{id}")
	public StudentResponse approveResponse(@PathVariable long id) {
		return studentService.approveStudent(id);
	}

	@DeleteMapping("/delete/{id}")
	public StudentResponse deleteStudent(@PathVariable long id) {
		return studentService.deleteStudent(id);
	}
}
