package com.ltf.classervice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ltf.classervice.dto.request.CreateClassRequest;
import com.ltf.classervice.dto.response.ClassInfoResponse;
import com.ltf.classervice.dto.response.ClassResponse;
import com.ltf.classervice.entities.Class;

@Service
public interface ClassService {
	ClassResponse createClass (CreateClassRequest request, long userId);
	List<Class> classes();
	List<Class> getUnapprovedClasses();
	List<Class> getApprovedClasses();
	Class getClassById(long id);
	List<ClassInfoResponse> getAllClasses();
	ClassInfoResponse getClassInfoById(long id);
}
