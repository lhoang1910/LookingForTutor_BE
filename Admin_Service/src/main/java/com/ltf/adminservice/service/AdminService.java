package com.ltf.adminservice.service;

import java.util.List;

import com.ltf.adminservice.dto.response.AdminResponse;
import com.ltf.adminservice.dto.response.ClassFee;
import com.ltf.adminservice.dto.response.TutorClassInfoResponse;

public interface AdminService {
	AdminResponse registerClass(long tutorId, long classId);

	AdminResponse approveTutorWithClass(long id);

	List<TutorClassInfoResponse> getAllUnapprovedClasses();
	
	ClassFee admissionClassFee (long id);

}