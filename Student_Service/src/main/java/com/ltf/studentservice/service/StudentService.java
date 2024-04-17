package com.ltf.studentservice.service;

import java.util.List;

import com.ltf.studentservice.dto.request.AddStudentRequest;
import com.ltf.studentservice.dto.response.StudentResponse;
import com.ltf.studentservice.entities.Student;

public interface StudentService {
	StudentResponse addStudent(AddStudentRequest addStudentRequest);
	StudentResponse approveStudent(long id);
	List<Student> listStudent();
	Student getStudentById(long id);
	StudentResponse deleteStudent(long id);
}
