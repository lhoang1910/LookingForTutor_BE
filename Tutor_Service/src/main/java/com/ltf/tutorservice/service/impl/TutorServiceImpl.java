package com.ltf.tutorservice.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ltf.tutorservice.client.UserServiceClient;
import com.ltf.tutorservice.dto.request.AddTutorProfileRquest;
import com.ltf.tutorservice.dto.response.TutorProfileResponse;
import com.ltf.tutorservice.dto.response.TutorResponse;
import com.ltf.tutorservice.dto.response.UserProfile;
import com.ltf.tutorservice.entity.Image;
import com.ltf.tutorservice.entity.Tutor;
import com.ltf.tutorservice.repository.ImageRepository;
import com.ltf.tutorservice.repository.TutorRepository;
import com.ltf.tutorservice.service.TutorService;

import jakarta.ws.rs.NotFoundException;

@Service
public class TutorServiceImpl implements TutorService {

	private final UserServiceClient userServiceClient;

	@Autowired
	TutorRepository tutorRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	public TutorServiceImpl(UserServiceClient userServiceClient) {
		this.userServiceClient = userServiceClient;
	}

	@Override
	public TutorResponse updateTutorProfile(String loggedInUser, AddTutorProfileRquest addTutorProfileRquest) {
		UserProfile userProfile = userServiceClient.getCurrentUser(loggedInUser);
		System.out.println(userProfile.getId());
		Optional<Tutor> optionalTutor = tutorRepository.findByUserId(userProfile.getId());

		Tutor tutor;
		if (optionalTutor.isPresent()) {
			tutor = optionalTutor.get();
			tutor.setSchool(addTutorProfileRquest.getSchool());
			tutor.setMajor(addTutorProfileRquest.getMajor());
			tutor.setStartTime(addTutorProfileRquest.getStartTime());
			tutor.setEndTime(addTutorProfileRquest.getEndTime());
			tutor.setHadExp(addTutorProfileRquest.isHadExp());
			tutor.setExpDescription(addTutorProfileRquest.getExpDescription());
		} else {
			tutor = new Tutor();
			tutor.setUserId(userProfile.getId());
			tutor.setSchool(addTutorProfileRquest.getSchool());
			tutor.setMajor(addTutorProfileRquest.getMajor());
			tutor.setStartTime(addTutorProfileRquest.getStartTime());
			tutor.setEndTime(addTutorProfileRquest.getEndTime());
			tutor.setHadExp(addTutorProfileRquest.isHadExp());
			tutor.setExpDescription(addTutorProfileRquest.getExpDescription());
		}

		tutorRepository.save(tutor);
		return new TutorResponse("Thông báo", "Cập nhật thông tin thành công");
	}

	@Override
	public TutorResponse updateTutorImg(String loggedInUser, MultipartFile cccd, MultipartFile studentCard) {
		UserProfile userProfile = userServiceClient.getCurrentUser(loggedInUser);
		Tutor tutor = tutorRepository.findByUserId(userProfile.getId())
				.orElseThrow(() -> new NotFoundException("Không tìm thấy tutor"));
		System.out.println(tutor.getId());

		Optional<Image> imageOptional = imageRepository.findByTutor(tutor);
		Image image = new Image();

		if (!imageOptional.isPresent()) {
			image.setTutor(tutor);
		}

		try {
			if (cccd != null && !cccd.isEmpty()) {
				byte[] cccdBytes = cccd.getBytes();
				image.setCccdImage(cccdBytes);
			}

			if (studentCard != null && !studentCard.isEmpty()) {
				byte[] studentCardBytes = studentCard.getBytes();
				image.setStudentCardImage(studentCardBytes);
			}

			imageRepository.save(image);
			return new TutorResponse("Thành công", "Cập nhật ảnh thành công");
		} catch (IOException e) {
			// Xử lý ngoại lệ nếu có lỗi khi đọc dữ liệu từ MultipartFile
			return new TutorResponse("Lỗi", "Đã xảy ra lỗi khi xử lý ảnh");
		}
	}

	@Override
	public TutorProfileResponse getCurrentTutor(String loggedInUser) {
		// Lấy thông tin người dùng từ UserService
		UserProfile userProfile = userServiceClient.getCurrentUser(loggedInUser);

		// Lấy thông tin của gia sư từ TutorService
		Optional<Tutor> optionalTutor = tutorRepository.findByUserId(userProfile.getId());
		Optional<Image> img = imageRepository.findByTutor(optionalTutor);

		if (optionalTutor.isPresent()) {
			Tutor tutor = optionalTutor.get();
			Image image = img.get();

			TutorProfileResponse tutorProfileResponse = new TutorProfileResponse();

			tutorProfileResponse.setFullName(userProfile.getFullName());
			tutorProfileResponse.setUsername(userProfile.getUsername());
			tutorProfileResponse.setAddress(userProfile.getAddress());
			tutorProfileResponse.setEmail(userProfile.getEmail());
			tutorProfileResponse.setNumber(userProfile.getNumber());
			tutorProfileResponse.setSex(userProfile.getSex());
			tutorProfileResponse.setDateOfBirth(userProfile.getDateOfBirth());

			tutorProfileResponse.setSchool(tutor.getSchool());
			tutorProfileResponse.setMajor(tutor.getMajor());
			tutorProfileResponse.setStartTime(tutor.getStartTime());
			tutorProfileResponse.setEndTime(tutor.getEndTime());
			tutorProfileResponse.setHadExp(tutor.isHadExp());
			tutorProfileResponse.setExpDescription(tutor.getExpDescription());
			tutorProfileResponse.setCccdImage(image.getCccdImage());
			tutorProfileResponse.setStudentCardImage(image.getStudentCardImage());

			return tutorProfileResponse;
		} else {
			throw new NotFoundException("Không tìm thấy thông tin của gia sư với ID: " + userProfile.getId());
		}
	}

	@Override
	public TutorProfileResponse getTutorInfoById(long tutorId) {
		TutorProfileResponse profileResponse = new TutorProfileResponse();
		Optional<Tutor> optionalTutor = tutorRepository.findById(tutorId);
		Optional<Image> img = imageRepository.findByTutor(optionalTutor);

		if (optionalTutor.isPresent()) {
			Tutor tutor = optionalTutor.get();
			Image image = img.get();

			UserProfile userProfile = userServiceClient.getUserInfoById(tutor.getUserId());

			TutorProfileResponse tutorProfileResponse = new TutorProfileResponse();

			tutorProfileResponse.setFullName(userProfile.getFullName());
			tutorProfileResponse.setUsername(userProfile.getUsername());
			tutorProfileResponse.setAddress(userProfile.getAddress());
			tutorProfileResponse.setEmail(userProfile.getEmail());
			tutorProfileResponse.setNumber(userProfile.getNumber());
			tutorProfileResponse.setSex(userProfile.getSex());
			tutorProfileResponse.setDateOfBirth(userProfile.getDateOfBirth());

			tutorProfileResponse.setSchool(tutor.getSchool());
			tutorProfileResponse.setMajor(tutor.getMajor());
			tutorProfileResponse.setStartTime(tutor.getStartTime());
			tutorProfileResponse.setEndTime(tutor.getEndTime());
			tutorProfileResponse.setHadExp(tutor.isHadExp());
			tutorProfileResponse.setExpDescription(tutor.getExpDescription());
			tutorProfileResponse.setCccdImage(image.getCccdImage());
			tutorProfileResponse.setStudentCardImage(image.getStudentCardImage());

			return tutorProfileResponse;
		} else {
			throw new NotFoundException("Không tìm thấy thông tin của gia sư với ID: " + tutorId);
		}
	}

	@Override
	public List<TutorProfileResponse> getALlTutor() {
		List<TutorProfileResponse> tutorProfiles = new ArrayList<>();

		// Lấy danh sách tất cả các gia sư từ cơ sở dữ liệu
		List<Tutor> tutors = tutorRepository.findAll();

		for (Tutor tutor : tutors) {
			// Lấy thông tin hình ảnh của gia sư
			Optional<Image> img = imageRepository.findByTutor(tutor);

			// Kiểm tra xem gia sư có tồn tại không
			if (tutor != null) {
				// Tạo một đối tượng TutorProfileResponse mới
				TutorProfileResponse tutorProfileResponse = new TutorProfileResponse();

				// Lấy thông tin user profile của gia sư từ userServiceClient
				UserProfile userProfile = userServiceClient.getUserInfoById(tutor.getUserId());

				// Thiết lập thông tin cho đối tượng TutorProfileResponse
				tutorProfileResponse.setFullName(userProfile.getFullName());
				tutorProfileResponse.setUsername(userProfile.getUsername());
				tutorProfileResponse.setAddress(userProfile.getAddress());
				tutorProfileResponse.setEmail(userProfile.getEmail());
				tutorProfileResponse.setNumber(userProfile.getNumber());
				tutorProfileResponse.setSex(userProfile.getSex());
				tutorProfileResponse.setDateOfBirth(userProfile.getDateOfBirth());
				tutorProfileResponse.setSchool(tutor.getSchool());
				tutorProfileResponse.setMajor(tutor.getMajor());
				tutorProfileResponse.setStartTime(tutor.getStartTime());
				tutorProfileResponse.setEndTime(tutor.getEndTime());
				tutorProfileResponse.setHadExp(tutor.isHadExp());
				tutorProfileResponse.setExpDescription(tutor.getExpDescription());

				// Nếu hình ảnh của gia sư tồn tại, thiết lập nó cho đối tượng TutorProfileResponse
				if (img.isPresent()) {
					Image image = img.get();
					tutorProfileResponse.setCccdImage(image.getCccdImage());
					tutorProfileResponse.setStudentCardImage(image.getStudentCardImage());
				}

				// Thêm đối tượng TutorProfileResponse vào danh sách
				tutorProfiles.add(tutorProfileResponse);
			}
		}

		return tutorProfiles;
	}

}
