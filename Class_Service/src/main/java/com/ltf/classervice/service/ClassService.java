package com.ltf.classervice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ltf.classervice.dto.request.CreateClassRequest;
import com.ltf.classervice.dto.response.ClassResponse;
import com.ltf.classervice.entities.Class;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public interface ClassService {
	ClassResponse createClass (CreateClassRequest request, @RequestHeader("loggedInUser") String loggedInUser);
	List<Class> classes();
	List<Class> getUnapprovedClasses();
	List<Class> getApprovedClasses();
	Class getClassById(long id);
	List<Class> studentClasseseById(long id);
	List<Class> currentStudentClasses(@RequestHeader("loggedInUser") String loggedInUser);
	ClassResponse approveClass(long id);
	ClassResponse deleteClass(long id);
	List<Class> getPaid();
	List<Class> getUnPaid();
	String hadTutor(long id);
	List<Class> getClassesPaidWithoutTutor();
	List<Class> getClassesHadTutor();
	String paid(long id);
}
