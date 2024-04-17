package com.ltf.adminservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ltf.adminservice.client.ClassClient;
import com.ltf.adminservice.client.TutorClient;
import com.ltf.adminservice.dto.response.AdminResponse;
import com.ltf.adminservice.dto.response.ClassFee;
import com.ltf.adminservice.dto.response.ClassInfoResponse;
import com.ltf.adminservice.dto.response.TutorClassInfoResponse;
import com.ltf.adminservice.dto.response.TutorProfileResponse;
import com.ltf.adminservice.entities.ClassManagerment;
import com.ltf.adminservice.repository.ClassManagermentRepository;
import com.ltf.adminservice.service.AdminService;

import jakarta.ws.rs.NotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	ClassManagermentRepository repository;

	@Autowired
	private TutorClient tutorClient;

	@Autowired
	private ClassClient classClient;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public AdminResponse approveTutorWithClass(long id) {
		ClassManagerment managerment = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Yêu cầu không tồn tai"));
		managerment.setApproved(true);
		repository.save(managerment);
		sendVerificationEmail(managerment);
		ClassInfoResponse classInfo = classClient.getClassInfoById(managerment.getId());
		return new AdminResponse("Thành công", "Duyệt lớp thành công");
	}

	@Override
	public AdminResponse registerClass(long tutorId, long classId) {
		ClassManagerment present = repository.findByTutorIdAndClassId(tutorId, classId);
		if (present != null) {
			return new AdminResponse("Bạn đã gửi yêu cầu", "Vui lòng chọn lớp khác");
		} else {
			ClassManagerment classManagerment = new ClassManagerment();
			classManagerment.setClassId(classId);
			classManagerment.setTutorId(tutorId);
			classManagerment.setApproved(false);
			repository.save(classManagerment);

			return new AdminResponse("Gửi yêu cầu nhận lớp thành công",
					"Yêu cầu của bạn sẽ được phản hồi trong vòng tối đa 24h");
		}

	}

	@Override
	public List<TutorClassInfoResponse> getAllUnapprovedClasses() {
		List<TutorClassInfoResponse> tutorClassInfoList = new ArrayList<>();

		// Lấy danh sách tất cả các thông tin của lớp học chưa được duyệt
		List<ClassManagerment> unapprovedClasses = repository.findByApprovedFalse();

		// Duyệt qua từng bản ghi trong danh sách unapprovedClasses
		for (ClassManagerment classManagement : unapprovedClasses) {
			// Lấy thông tin của gia sư
			TutorProfileResponse tutorProfile = tutorClient.tutorProfileResponse(classManagement.getTutorId());

			// Lấy thông tin của lớp học
			ClassInfoResponse classInfo = classClient.getClassInfoById(classManagement.getClassId());

			// Tạo đối tượng DTO chứa thông tin của lớp học và thông tin của gia sư
			TutorClassInfoResponse tutorClassInfo = new TutorClassInfoResponse();
			tutorClassInfo.setClassId(classInfo.getClassId());
			tutorClassInfo.setSubject(classInfo.getSubject());
			tutorClassInfo.setGrade(classInfo.getGrade());
			tutorClassInfo.setNumberOfWeek(classInfo.getNumberOfWeek());
			tutorClassInfo.setTutorSex(classInfo.getTutorSex());
			tutorClassInfo.setClassTime(classInfo.getClassTime());
			tutorClassInfo.setFurtherDescription(classInfo.getFurtherDescription());
			tutorClassInfo.setAddress(classInfo.getAddress());
			tutorClassInfo.setStudentFullName(classInfo.getStudentFullName());
			tutorClassInfo.setStudentOld(classInfo.getStudentOld());
			tutorClassInfo.setStudentSex(classInfo.getStudentSex());
			tutorClassInfo.setAdmissionFee(classInfo.getAdmissionFee());
			tutorClassInfo.setTution(classInfo.getTution());
			tutorClassInfo.setStudentGrade(classInfo.getStudentGrade());

			tutorClassInfo.setTutorId(tutorProfile.getTutorId());
			tutorClassInfo.setUserId(tutorProfile.getUserId());
			tutorClassInfo.setFullName(tutorProfile.getFullName());
			tutorClassInfo.setUsername(tutorProfile.getUsername());
			tutorClassInfo.setAddress(tutorProfile.getAddress());
			tutorClassInfo.setEmail(tutorProfile.getEmail());
			tutorClassInfo.setNumber(tutorProfile.getNumber());
			tutorClassInfo.setSchool(tutorProfile.getSchool());
			tutorClassInfo.setMajor(tutorProfile.getMajor());
			tutorClassInfo.setStartTime(tutorProfile.getStartTime());
			tutorClassInfo.setEndTime(tutorProfile.getEndTime());
			tutorClassInfo.setHadExp(tutorProfile.isHadExp());
			tutorClassInfo.setExpDescription(tutorProfile.getExpDescription());

			tutorClassInfoList.add(tutorClassInfo);
		}

		return tutorClassInfoList;
	}

	public void sendVerificationEmail(ClassManagerment managerment) {
		String subject = "Trung tâm gia sư việt: ";
		String body = "Xin chúc mừng gia sư " + managerment.getTutorId() + " đã nhận thành công lớp "
				+ managerment.getTutorId()
				+ " , vui lòng thanh toán phí nhận lớp trong vòng 24h để hoàn tất nhật lớp, nếu không lớp sẽ bị hủy.\n"
				+ "Mã thanh toán của bạn là " + managerment.getId();

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(tutorClient.tutorProfileResponse(managerment.getTutorId()).getEmail());
		message.setSubject(subject);
		message.setText(body);

		javaMailSender.send(message);
	}

	@Override
	public ClassFee admissionClassFee(long id) {
		ClassManagerment managerment = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Yêu cầu không tồn tại"));
		ClassInfoResponse classInfo = classClient.getClassInfoById(managerment.getClassId());
		return new ClassFee(classInfo.getAdmissionFee(), classInfo.getTution(),
				(classInfo.getAdmissionFee() * classInfo.getTution() * 0.1));
	}

}
