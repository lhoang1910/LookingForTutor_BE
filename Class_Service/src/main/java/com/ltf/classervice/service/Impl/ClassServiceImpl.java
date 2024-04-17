
package com.ltf.classervice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltf.classervice.client.StudentClient;
import com.ltf.classervice.dto.request.CreateClassRequest;
import com.ltf.classervice.dto.response.ClassInfoResponse;
import com.ltf.classervice.dto.response.ClassResponse;
import com.ltf.classervice.dto.response.StudentResponse;
import com.ltf.classervice.entities.Class;
import com.ltf.classervice.repository.ClassRepository;
import com.ltf.classervice.service.ClassService;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassRepository classRepository;

	private final StudentClient studentClient;

	@Autowired
	public ClassServiceImpl(StudentClient studentClient) {
		super();
		this.studentClient = studentClient;
	}

	@Override
	@Transactional
	public ClassResponse createClass(CreateClassRequest classDTO, long userId) {

		StudentResponse studentResponse = studentClient.getStudentInfo(userId);

		if (studentResponse != null && studentResponse.isApproved()) {
			Class newClass = new Class();
			newClass.setSubject(classDTO.getSubject());
			newClass.setGrade(classDTO.getGrade());
			newClass.setNumberOfWeek(classDTO.getNumberOfWeek());
			newClass.setTutorSex(classDTO.getTutorSex());
			newClass.setClassTime(classDTO.getClassTime());
			newClass.setFurtherDescription(classDTO.getFurtherDescription());
			newClass.setAdmissionFee(classDTO.getAdmissionFee());
			newClass.setAddress(classDTO.getAddress());
			newClass.setHadTutor(false);
			newClass.setStudentId(userId);
			newClass.setTution(classDTO.getTution());
			classRepository.save(newClass);

			return new ClassResponse("Thành công", "Thêm lớp học thành công");
		} else {
			return new ClassResponse("Lỗi", "Học sinh không tồn tại hoặc chưa được phê duyệt");
		}
	}

	@Override
	public List<Class> classes() {
		// TODO Auto-generated method stub
		List<Class> classes = classRepository.findAll();
		return classes;
	}

	@Override
	public List<Class> getUnapprovedClasses() {
		return classRepository.findByHadTutorFalse();
	}

	@Override
	public List<Class> getApprovedClasses() {
		return classRepository.findByHadTutorTrue();
	}

	@Override
	public Class getClassById(long id) {
		return classRepository.findById(id).orElseThrow(() -> new NotFoundException("Lớp học không tồn tại"));
	}

	@Override
	public List<ClassInfoResponse> getAllClasses() {
		List<Class> classes = classRepository.findByHadTutorFalse();
		List<ClassInfoResponse> classInfoResponses = new ArrayList<>();

		for (Class classEntity : classes) {
			ClassInfoResponse classInfoResponse = new ClassInfoResponse();
			classInfoResponse.setClassId(classEntity.getId());
			classInfoResponse.setSubject(classEntity.getSubject());
			classInfoResponse.setGrade(classEntity.getGrade());
			classInfoResponse.setNumberOfWeek(classEntity.getNumberOfWeek());
			classInfoResponse.setTutorSex(classEntity.getTutorSex());
			classInfoResponse.setClassTime(classEntity.getClassTime());
			classInfoResponse.setFurtherDescription(classEntity.getFurtherDescription());
			classInfoResponse.setTution(classEntity.getTution());
			classInfoResponse.setAddress(classEntity.getAddress());
			classInfoResponse.setAdmissionFee(classEntity.getAdmissionFee());

			StudentResponse studentResponse = studentClient.getStudentInfo(classEntity.getStudentId());
			if (studentResponse != null) {
				classInfoResponse.setStudentFullName(studentResponse.getFullName());
				classInfoResponse.setStudentOld(studentResponse.getOld());
				classInfoResponse.setStudentSex(studentResponse.getSex());
				classInfoResponse.setStudentGrade(studentResponse.getGrade());
			}

			classInfoResponses.add(classInfoResponse);
		}

		return classInfoResponses;
	}

	@Override
	public ClassInfoResponse getClassInfoById(long id) {
		// Tìm kiếm thông tin lớp theo ID và hadTutor bằng false
		Class classEntity = classRepository.findByIdAndHadTutorFalse(id);
		if (classEntity == null) {
			return null;
		}
		ClassInfoResponse classInfoResponse = new ClassInfoResponse();
		classInfoResponse.setClassId(classEntity.getId());
		classInfoResponse.setSubject(classEntity.getSubject());
		classInfoResponse.setGrade(classEntity.getGrade());
		classInfoResponse.setNumberOfWeek(classEntity.getNumberOfWeek());
		classInfoResponse.setTutorSex(classEntity.getTutorSex());
		classInfoResponse.setClassTime(classEntity.getClassTime());
		classInfoResponse.setFurtherDescription(classEntity.getFurtherDescription());
		classInfoResponse.setTution(classEntity.getTution());
		classInfoResponse.setAdmissionFee(classEntity.getAdmissionFee());
		classInfoResponse.setAddress(classEntity.getAddress());

		// Lấy thông tin học sinh từ StudentService
		StudentResponse studentResponse = studentClient.getStudentInfo(classEntity.getStudentId());
		if (studentResponse != null) {
			classInfoResponse.setStudentFullName(studentResponse.getFullName());
			classInfoResponse.setStudentOld(studentResponse.getOld());
			classInfoResponse.setStudentSex(studentResponse.getSex());
			classInfoResponse.setStudentGrade(studentResponse.getGrade());
		}
		return classInfoResponse;
	}

}
