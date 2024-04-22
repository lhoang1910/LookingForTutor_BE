package com.ltf.studentservice.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ltf.studentservice.client.UserClient;
import com.ltf.studentservice.dto.response.StudentProfileResponse;
import com.ltf.studentservice.dto.response.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltf.studentservice.dto.request.AddStudentRequest;
import com.ltf.studentservice.dto.response.StudentResponse;
import com.ltf.studentservice.entities.Student;
import com.ltf.studentservice.repository.StudentRepository;
import com.ltf.studentservice.service.StudentService;

import jakarta.ws.rs.NotFoundException;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.swing.text.html.Option;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository repository;

	@Autowired
	UserClient userClient;

	@Override
	public StudentResponse updateStudentProfile(@RequestHeader("loggedInUser") String loggedInUser, AddStudentRequest addStudentRequest){
		UserProfile userProfile = userClient.getCurrentUser(loggedInUser);
		Optional<Student> studentOptional = repository.findByUserId(userProfile.getId());

		Student student;
		if (studentOptional.isPresent()){
			student = studentOptional.get();
			student.setGrade(addStudentRequest.getGrade());
			student.setFurtherDescription(addStudentRequest.getFurtherDescription());
			student.setOld(addStudentRequest.getOld());
		} else {
			student = new Student();
			student.setUserId(userProfile.getId());
			student.setGrade(addStudentRequest.getGrade());
			student.setFurtherDescription(addStudentRequest.getFurtherDescription());
			student.setOld(addStudentRequest.getOld());
		}
		repository.save(student);

		return new StudentResponse("Thông báo", "Đăng ký học sinh thành công, vui lòng chờ duyệt");
	}

	@Override
	public List<StudentProfileResponse> listStudent() {
		List<StudentProfileResponse> studentProfiles = new ArrayList<>();

		// Lấy danh sách tất cả học sinh từ cơ sở dữ liệu
		List<Student> students = repository.findAll();

		// Chuyển đổi từng học sinh thành StudentProfileResponse và thêm vào danh sách
		for (Student student : students) {
			UserProfile userProfile = userClient.getUserInfoById(student.getUserId());

			StudentProfileResponse studentProfileResponse = new StudentProfileResponse();
			studentProfileResponse.setAddress(userProfile.getAddress());
			studentProfileResponse.setEmail(userProfile.getEmail());
			studentProfileResponse.setFullName(userProfile.getFullName());
			studentProfileResponse.setNumber(userProfile.getNumber());
			studentProfileResponse.setOld(student.getOld());
			studentProfileResponse.setFurtherDescription(student.getFurtherDescription());
			studentProfileResponse.setGrade(student.getGrade());
			studentProfileResponse.setSex(userProfile.getSex());
			studentProfileResponse.setDateOfBirth(userProfile.getDateOfBirth());

			studentProfiles.add(studentProfileResponse);
		}

		return studentProfiles;
	}


	@Override
	public StudentProfileResponse getStudentById(long id) {
		Optional<Student> studentOptional = repository.findById(id);
		UserProfile userProfile = userClient.getUserInfoById(studentOptional.get().getUserId());


		if (studentOptional.isPresent()){
			Student student = studentOptional.get();

			StudentProfileResponse studentProfileResponse = new StudentProfileResponse();
			studentProfileResponse.setAddress(userProfile.getAddress());
			studentProfileResponse.setEmail(userProfile.getEmail());
			studentProfileResponse.setFullName(userProfile.getFullName());
			studentProfileResponse.setNumber(userProfile.getNumber());
			studentProfileResponse.setOld(student.getOld());
			studentProfileResponse.setFurtherDescription(student.getFurtherDescription());
			studentProfileResponse.setGrade(student.getGrade());
			studentProfileResponse.setSex(userProfile.getSex());
			studentProfileResponse.setDateOfBirth(userProfile.getDateOfBirth());
			return studentProfileResponse;
		} else {
			throw new NotFoundException("Khong tim thay hoc sinh voi id tren");
		}
	}

	@Override
	public StudentProfileResponse getCurrentStudentResponse(String loggedInUser) {
		UserProfile userProfile = userClient.getCurrentUser(loggedInUser);
		Optional<Student> studentOptional = repository.findByUserId(userProfile.getId());

		if (studentOptional.isPresent()){
			Student student = studentOptional.get();

			StudentProfileResponse studentProfileResponse = new StudentProfileResponse();
			studentProfileResponse.setAddress(userProfile.getAddress());
			studentProfileResponse.setEmail(userProfile.getEmail());
			studentProfileResponse.setFullName(userProfile.getFullName());
			studentProfileResponse.setNumber(userProfile.getNumber());
			studentProfileResponse.setOld(student.getOld());
			studentProfileResponse.setFurtherDescription(student.getFurtherDescription());
			studentProfileResponse.setGrade(student.getGrade());
			studentProfileResponse.setSex(userProfile.getSex());
			studentProfileResponse.setDateOfBirth(userProfile.getDateOfBirth());
			return studentProfileResponse;
		} else {
			throw new NotFoundException("Khong tim thay hoc sinh voi id tren");
		}
	}

}
