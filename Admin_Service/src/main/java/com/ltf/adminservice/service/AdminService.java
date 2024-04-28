package com.ltf.adminservice.service;

import java.util.List;

import com.ltf.adminservice.dto.response.AdminResponse;
import com.ltf.adminservice.dto.response.ListChatTutor;
import com.ltf.adminservice.dto.response.TutorClassInfoResponse;
import com.ltf.adminservice.dto.response.TutorProfileResponse;
import com.ltf.adminservice.entities.ClassManagerment;
import org.springframework.web.bind.annotation.RequestHeader;

public interface AdminService {
	AdminResponse registerClass(@RequestHeader("loggedInUser") String loggedInUser, long classId);

	AdminResponse approveTutorWithClass(long id);

	List<TutorClassInfoResponse> getAllUnapprovedClasses();

	List<TutorClassInfoResponse> getAllApprovedClasses();

	List<ClassManagerment> getAllCLassManagerment();

	List<ClassManagerment> getAllApproved();

	List<ClassManagerment> getAllUnapproved();

	List<ClassManagerment> getALlPaid();

	List<ClassManagerment> getAllUnPaid();

	ClassManagerment getByClassId(long classId);

	List<ClassManagerment> getAllByTutorId(long tutorId);

	String paid(long classId);

	ClassManagerment findById(long id);

	List<ListChatTutor> listChatForTutor(@RequestHeader("loggedInUser") String loggedInUser);

	TutorProfileResponse getTutorResponseByClassId(long classId);

}