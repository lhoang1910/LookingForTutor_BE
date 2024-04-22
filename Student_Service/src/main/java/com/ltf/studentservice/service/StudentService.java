package com.ltf.studentservice.service;

import java.util.List;

import com.ltf.studentservice.dto.request.AddStudentRequest;
import com.ltf.studentservice.dto.response.StudentProfileResponse;
import com.ltf.studentservice.dto.response.StudentResponse;
import com.ltf.studentservice.entities.Student;
import org.springframework.web.bind.annotation.RequestHeader;

public interface StudentService {
	StudentResponse updateStudentProfile(@RequestHeader("loggedInUser") String loggedInUser, AddStudentRequest addStudentRequest);
	List<StudentProfileResponse> listStudent();
	StudentProfileResponse getStudentById(long id);
	StudentProfileResponse getCurrentStudentResponse(@RequestHeader("loggedInUser") String loggedInUser);

}
