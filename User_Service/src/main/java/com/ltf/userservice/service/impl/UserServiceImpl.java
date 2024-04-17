package com.ltf.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ltf.userservice.dto.request.CreateAccountRequest;
import com.ltf.userservice.dto.request.ResetPasswordRequest;
import com.ltf.userservice.dto.response.AccountResponse;
import com.ltf.userservice.dto.response.UserProfileResponse;
import com.ltf.userservice.entities.User;
import com.ltf.userservice.repository.UserRepository;
import com.ltf.userservice.service.UserService;

import jakarta.ws.rs.NotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserProfileResponse getCurrentUser(@RequestHeader("loggedInUser") String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Tai khoan dang nhap khong ton tai"));
		UserProfileResponse userProfileResponse = new UserProfileResponse();
		userProfileResponse.setId(user.getId());
		userProfileResponse.setSex(user.getSex());
		userProfileResponse.setDateOfBirth(user.getDateOfBirth());
		userProfileResponse.setEmail(user.getEmail());
		userProfileResponse.setNumber(user.getNumber());
		userProfileResponse.setFullName(user.getFullName());
		userProfileResponse.setUsername(user.getUsername());
		return userProfileResponse;
	}

	@Override
	public AccountResponse updateProfile(CreateAccountRequest request) {
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new NotFoundException("Vui lòng đăng nhập"));
		user.setDateOfBirth(request.getDateOfBirth());
		user.setEmail(request.getEmail());
		user.setFullName(request.getFullName());
		user.setNumber(request.getNumber());
		user.setSex(request.getSex());
		userRepository.save(user);
		return new AccountResponse("Thông báo", "Sửa thông tin thành công");
	}

	@Override
	public UserProfileResponse getUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Người dùng không tồn tại"));
		UserProfileResponse response = new UserProfileResponse();
		response.setId(user.getId());
		response.setDateOfBirth(user.getDateOfBirth());
		response.setEmail(user.getEmail());
		response.setFullName(user.getFullName());
		response.setNumber(user.getNumber());
		response.setSex(user.getSex());
		response.setUsername(user.getUsername());
		return response;
	}

	@Override
	public AccountResponse changePassword(@RequestHeader("loggedInUser") String username,
			ResetPasswordRequest request) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Tài khoản không tồn tại"));
		if (passwordEncoder.encode(request.getOldPassword()).equals(user.getPassword())) {
			if (request.getNewPassWord().equals(request.getConfirmNewPassword())) {
				user.setPassword(request.getNewPassWord());
			} else {
				return new AccountResponse("Thất bại", "Mật khẩu không khớp");
			}
		} else {
			return new AccountResponse("Thất bại", "Mật khẩu cũ không đúng");
		}
		return new AccountResponse("Thành công", "Đổi mật khẩu thành công");
	}

}