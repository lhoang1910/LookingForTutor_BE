package com.ltf.studentservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltf.studentservice.dto.request.AddStudentRequest;
import com.ltf.studentservice.dto.response.StudentResponse;
import com.ltf.studentservice.entities.Student;
import com.ltf.studentservice.repository.StudentRepository;
import com.ltf.studentservice.service.StudentService;

import jakarta.ws.rs.NotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository repository;

	@Override
	public StudentResponse addStudent(AddStudentRequest addStudentRequest) {
		Student student = new Student();
		student.setFullName(addStudentRequest.getFullName());
		student.setApproved(false);
		student.setEmail(addStudentRequest.getEmail());
		student.setFurtherDescription(addStudentRequest.getFurtherDescription());
		student.setGrade(addStudentRequest.getGrade());
		student.setOld(addStudentRequest.getOld());
		student.setPhoneNumber(addStudentRequest.getPhoneNumber());
		student.setSex(addStudentRequest.getSex());

		repository.save(student);

		return new StudentResponse("Thông báo", "Đăng ký học sinh thành công, vui lòng chờ duyệt");
	}

	@Override
	public StudentResponse approveStudent(long id) {
		Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("Học sinh không tồn tại"));
		student.setApproved(true);
		repository.save(student);
		return new StudentResponse("Thông báo", "Duyệt thành công");
	}

	@Override
	public List<Student> listStudent() {
		List<Student> students = repository.findAll();
		return students;
	}

	@Override
	public Student getStudentById(long id) {
		Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("Học sinh không tồn tại"));
		return student;
	}

	@Override
	public StudentResponse deleteStudent(long id) {
		repository.deleteById(id);
		return new StudentResponse("Thông báo", "Xóa học sinh thành công");
	}

}
