package com.ltf.adminservice.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ltf.adminservice.client.PaymentClient;
import com.ltf.adminservice.dto.request.CreateBillRequest;
import com.ltf.adminservice.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ltf.adminservice.client.ClassClient;
import com.ltf.adminservice.client.TutorClient;
import com.ltf.adminservice.entities.ClassManagerment;
import com.ltf.adminservice.repository.ClassManagermentRepository;
import com.ltf.adminservice.service.AdminService;

import jakarta.ws.rs.NotFoundException;
import org.springframework.web.bind.annotation.RequestHeader;

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

	@Autowired
	private PaymentClient paymentClient;

	@Override
	public AdminResponse approveTutorWithClass(long id) {
		ClassManagerment managerment = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Yêu cầu không tồn tai"));
		managerment.setApproved(true);
		repository.save(managerment);
		TutorProfileResponse tutor = tutorClient.tutorProfileResponse(managerment.getTutorId());
		sendVerificationEmail(tutor ,managerment);
		ClassInfoResponse classInfo = classClient.getClassInfoById(managerment.getId());

		CreateBillRequest createBillRequest = new CreateBillRequest();
		createBillRequest.setPaid(false);
		createBillRequest.setPaymentCode("TUT"+managerment.getClassId());
		TutorProfileResponse tutorProfileResponse = new TutorProfileResponse();
		createBillRequest.setUserId(tutor.getUserId());
		ClassInfoResponse aClass = classClient.getClassInfoById(managerment.getId());
		long aOM = (long) (aClass.getAdmissionFee() * aClass.getTution() * 0.1);
		createBillRequest.setAmountOfMoney(aOM);
		createBillRequest.setDescription(String.valueOf(classInfo.getClassId()));
		paymentClient.createBill(createBillRequest);

		return new AdminResponse("Thành công", "Duyệt lớp thành công");
	}

	@Override
	public AdminResponse registerClass(@RequestHeader("loggedInUser") String loggedInUser, long classId) {
		TutorProfileResponse tutor = tutorClient.getCurrentTutorProfile(loggedInUser);
		ClassManagerment present = repository.findByTutorIdAndClassId(tutor.getTutorId(), classId);
		if (present != null) {
			return new AdminResponse("Bạn đã gửi yêu cầu", "Vui lòng chọn lớp khác");
		} else {
			ClassManagerment classManagerment = new ClassManagerment();
			classManagerment.setClassId(classId);
			classManagerment.setTutorId(tutor.getTutorId());
			classManagerment.setApproved(false);
			repository.save(classManagerment);

			return new AdminResponse("Gửi yêu cầu nhận lớp thành công",
					"Vui lòng chờ phản hồi từ admin");
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
			tutorClassInfo.setStartTime(classInfo.getStartTime());
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

	@Override
	public List<TutorClassInfoResponse> getAllApprovedClasses() {
		List<TutorClassInfoResponse> tutorClassInfoList = new ArrayList<>();

		List<ClassManagerment> approvedClasses = repository.findByApprovedTrue();

		for (ClassManagerment classManagement : approvedClasses) {
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
			tutorClassInfo.setStartTime(classInfo.getStartTime());
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

	@Override
	public List<ClassManagerment> getAllCLassManagerment() {
		List<ClassManagerment> cM = repository.findAll();
		return cM;
	}

	@Override
	public List<ClassManagerment> getAllApproved() {
		List<ClassManagerment> cM = repository.findAllByApproved(true);
		return cM;
	}

	@Override
	public List<ClassManagerment> getAllUnapproved() {
		List<ClassManagerment> cM = repository.findAllByApproved(false);
		return cM;
	}

	@Override
	public List<ClassManagerment> getALlPaid() {
		List<ClassManagerment> cM = repository.findAllByPaid(true);
		return cM;
	}

	@Override
	public List<ClassManagerment> getAllUnPaid() {
		List<ClassManagerment> cM = repository.findAllByPaid(false);
		return cM;
	}

	@Override
	public ClassManagerment getByClassId(long classId) {
		ClassManagerment managerment = repository.findByClassId(classId);
		return managerment;
	}

	@Override
	public List<ClassManagerment> getAllByTutorId(long tutorId) {
		List<ClassManagerment> cMS = repository.findAllByTutorId(tutorId);
		return cMS;
	}

	@Override
	public String paid(long classId) {
		ClassManagerment managerment = repository.findByClassId(classId);
		managerment.setPaid(true);
		repository.save(managerment);
		return "Đã thanh toán";
	}

	@Override
	public ClassManagerment findById(long id) {
		ClassManagerment managerment = repository.findByClassId(id);
		return null;
	}

	@Override
	public List<ListChatTutor> listChatForTutor(String loggedInUser) {
		TutorProfileResponse tutor = tutorClient.getCurrentTutorProfile(loggedInUser);
		List<ListChatTutor> listChatTutors = new ArrayList<>();
		List<ClassManagerment> managerments = repository.findAllByTutorIdAndPaid(tutor.getTutorId(), true);
		for (ClassManagerment managerment : managerments){
			ListChatTutor chatTutor = new ListChatTutor();
			long classId = managerment.getClassId();
			ClassInfoResponse classInfoResponse = classClient.getClassInfoById(classId);
			chatTutor.setUsername(classInfoResponse.getStudentFullName());
			chatTutor.setId(classInfoResponse.getStudentId());
			listChatTutors.add(chatTutor);
		}

		return listChatTutors;
	}

	@Override
	public TutorProfileResponse getTutorResponseByClassId(long classId) {
		ClassManagerment managerment = repository.findByClassIdAndPaid(classId, true);
		TutorProfileResponse response = tutorClient.tutorProfileResponse(managerment.getTutorId());
		return response;
	}

	private void sendVerificationEmail(TutorProfileResponse tutor, ClassManagerment managerment) {
		String subject = "THÔNG BÁO VỀ ĐĂNG KÝ LỚP HỌC: ";
		String body = "Kính chào " + tutor.getFullName() + ", lớp của bạn đa được duyệt, vui lòng thanh toán phí đăng ký lớp trong 24h \n " +
				"mã thanh toán là: " + "TUTOR"+managerment.getClassId();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(tutor.getEmail());
		message.setSubject(subject);
		message.setText(body);

		javaMailSender.send(message);
	}

}
