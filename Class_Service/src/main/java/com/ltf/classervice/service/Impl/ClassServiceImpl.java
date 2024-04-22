
package com.ltf.classervice.service.Impl;

import com.ltf.classervice.client.PaymentClient;
import com.ltf.classervice.dto.request.CreateBillRequest;
import com.ltf.classervice.dto.request.CreateClassRequest;
import com.ltf.classervice.dto.response.ClassResponse;
import com.ltf.classervice.dto.response.StudentResponse;
import com.ltf.classervice.entities.Class;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ltf.classervice.client.StudentClient;
import com.ltf.classervice.repository.ClassRepository;
import com.ltf.classervice.service.ClassService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassRepository classRepository;

	@Autowired
	StudentClient studentClient;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	PaymentClient paymentClient;

	@Override
	public ClassResponse createClass(CreateClassRequest request, String loggedInUser) {

		StudentResponse student= studentClient.currentStudentProfile(loggedInUser);

		Class newClass = new Class();
		newClass.setStudentId(student.getStudentId());
		newClass.setTutorSex(request.getTutorSex());
		newClass.setAddress(request.getAddress());
		newClass.setAdmissionFee(request.getAdmissionFee());
		newClass.setApproved(false);
		newClass.setFurtherDescription(request.getFurtherDescription());
		newClass.setGrade(request.getGrade());
		newClass.setHoursPerSession(request.getHoursPerSession());
		newClass.setNumberOfWeek(request.getNumberOfWeek());
		newClass.setStartTime(request.getStartTime());
		newClass.setSubject(request.getSubject());
		newClass.setTution(request.getTution());
		newClass.setHadTutor(false);
		newClass.setPaid(false);

		return new ClassResponse("Đăng ký lớp thành công", "Vui lòng chờ phản hồi từ admin");
	}

	@Override
	public List<Class> classes() {
		List<Class> classes = classRepository.findAll();
		return classes;
	}

	@Override
	public List<Class> getUnapprovedClasses() {
		List<Class> unapprovedClasses = classRepository.findByIsApprovedFalse();
		return unapprovedClasses;
	}

	@Override
	public List<Class> getApprovedClasses() {
		List<Class> approvedClasses = classRepository.findByIsApprovedTrue();
		return approvedClasses;
	}

	@Override
	public Class getClassById(long id) {
		Class aClass = classRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tồn tại lớp với id này"));
		return aClass;
	}

	@Override
	public List<Class> studentClasseseById(long id) {
		List<Class> classes = classRepository.findAllByStudentId(id);
		return classes;
	}

	@Override
	public List<Class> currentStudentClasses(String loggedInUser) {
		StudentResponse studentResponse = studentClient.currentStudentProfile(loggedInUser);
		List<Class> classes = classRepository.findAllByStudentId(studentResponse.getStudentId());
		return classes;
	}

	@Override
	public ClassResponse approveClass(long id) {
		Class aClass = classRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy class với id này"));
		aClass.setApproved(true);
		aClass.setTimeApprove(LocalDateTime.now());
		classRepository.save(aClass);
		CreateBillRequest createBillRequest = new CreateBillRequest();
		createBillRequest.setPaymentCode("CLASS"+aClass.getId());
		long aOM = (long) (aClass.getAdmissionFee() * aClass.getTution() * 0.1);
		createBillRequest.setAmountOfMoney(aOM);
		createBillRequest.setPaid(false);
		StudentResponse student = studentClient.getStudent(aClass.getStudentId());
		createBillRequest.setUserId(student.getUserId());
		createBillRequest.setDescription(String.valueOf(aClass.getId()));
		paymentClient.createBill(createBillRequest);
		return new ClassResponse("Duyệt lớp thành công", "Lớp vào trạng thái chờ thanh toán");
	}

	@Override
	public ClassResponse deleteClass(long id) {
		Class aClass = classRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy class với id này"));
		classRepository.deleteById(aClass.getId());
		return new ClassResponse("Xóa lớp thành công", "Mã lớp trống");
	}

	@Override
	public List<Class> getPaid() {
		List<Class> classes = classRepository.findAllByIsPaidTrue();
		return classes;
	}

	@Override
	public List<Class> getUnPaid() {
		List<Class> classes = classRepository.findAllByIsPaidFalse();
		return classes;
	}

	@Override
	public String hadTutor(long id) {
		Class aClass = classRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Không tồn tại class với id này"));
		aClass.setHadTutor(true);
		classRepository.save(aClass);
		return "Thêm gia sư thành công";
	}

	@Override
	public List<Class> getClassesPaidWithoutTutor() {
		List<Class> classes = classRepository.findByIsPaidAndHadTutor(true, false);
		return classes;
	}

	@Override
	public List<Class> getClassesHadTutor() {
		List<Class> classes = classRepository.findByIsPaidAndHadTutor(true, true);
		return classes;
	}

	private void sendVerificationEmail(StudentResponse studentResponse, Class aClass) {
		String subject = "THÔNG BÁO VỀ ĐĂNG KÝ LỚP HỌC: ";
		String body = "Kính chào " + studentResponse.getFullName() + ", lớp của bạn đa được duyệt, vui lòng thanh toán phí đăng ký lớp trong 24h \n " +
				"mã thanh toán là: " + "CLASS"+aClass.getId();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(studentResponse.getEmail());
		message.setSubject(subject);
		message.setText(body);

		javaMailSender.send(message);
	}
}
