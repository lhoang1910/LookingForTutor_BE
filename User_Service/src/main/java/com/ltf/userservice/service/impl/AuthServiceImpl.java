package com.ltf.userservice.service.impl;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltf.userservice.dto.request.CreateAccountRequest;
import com.ltf.userservice.dto.request.SignInRequest;
import com.ltf.userservice.dto.request.VerifyRequest;
import com.ltf.userservice.dto.response.AccountResponse;
import com.ltf.userservice.entities.Role;
import com.ltf.userservice.entities.User;
import com.ltf.userservice.entities.VerificationCode;
import com.ltf.userservice.repository.UserRepository;
import com.ltf.userservice.repository.VerificationCodeRepository;
import com.ltf.userservice.service.AuthService;
import com.ltf.userservice.service.JwtService;

import jakarta.ws.rs.NotFoundException;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationCodeRepository verificationCodeRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	public Optional<User> existingEmail(String email) {
		// Find user by email
		return userRepository.findByEmail(email);
	}

	public Optional<User> existingUser(String username) {
		// Find user by email
		return userRepository.findByUsername(username);
	}

	@Override
	public AccountResponse signUp(CreateAccountRequest createAccountRequest) {
		// TODO Auto-generated method stub
		AccountResponse accountResponse = new AccountResponse();
		accountResponse.setTittle("Thông báo hệ thống");

		Optional<User> existingEmail = userRepository.findByEmail(createAccountRequest.getEmail());
		Optional<User> existingUsername = userRepository.findByUsername(createAccountRequest.getUsername());

		if (existingEmail.isPresent()) {
			accountResponse.setDescription("Email đã tồn tại");
		} else {
			if (existingUsername.isPresent()) {
				accountResponse.setDescription("Username đã tồn tại");
			} else {
				if (createAccountRequest.getConfirmPassword().equals(createAccountRequest.getPassword())) {
					// Tạo mới User
					User user = new User();
					user.setFullName(createAccountRequest.getFullName());
					user.setAddress(createAccountRequest.getAddress());
					user.setEmail(createAccountRequest.getEmail());
					user.setNumber(createAccountRequest.getNumber());
					user.setUsername(createAccountRequest.getUsername());
					user.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));

					// Thiết lập Role
					if (createAccountRequest.getRole() == null) {
						user.setRole(Role.ROLE_TUTOR); // Gia sư mặc định
					} else if (createAccountRequest.getRole().equals("admin")) {
						user.setRole(Role.ROLE_ADMIN);
					} else {
						user.setRole(Role.ROLE_STUDENT);
					}

					// Lưu User vào cơ sở dữ liệu
					User savedUser = userRepository.save(user);

					// Tạo mới VerificationCode và thiết lập User
					VerificationCode verificationCode = new VerificationCode();
					verificationCode.setVerified(false);
					Random rand = new Random();
					int randomNumber = rand.nextInt(900000) + 100000;
					verificationCode.setVerificationToken(randomNumber);

					verificationCodeRepository.save(verificationCode);

					// Gửi email xác nhận
					sendVerificationEmail(savedUser, verificationCode);

					accountResponse.setDescription("Đăng ký thành công");
				}
			}
		}
		return accountResponse;
	}

	private void sendVerificationEmail(User user, VerificationCode verificationCode) {
		String subject = "MÃ XÁC NHẬN TÀI KHOẢN: ";
		String body = "Kính chào " + user.getFullName() + ", vui lòng nhập mã xác thực sau vào ô xác thực:\n"
				+ verificationCode.getVerificationToken() + "\n (mã xác thực có hiệu lực trong vòng 5 phút)";

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(user.getEmail());
		message.setSubject(subject);
		message.setText(body);

		javaMailSender.send(message);
	}

	@Override
	@Transactional
	public AccountResponse verifyAccount(VerifyRequest verifyRequest) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(verifyRequest.getEmail())
				.orElseThrow(() -> new NotFoundException("Email chưa được đăng kí"));
		AccountResponse accountResponse = new AccountResponse();
		accountResponse.setTittle("Thông báo");

		VerificationCode verificationCode = verificationCodeRepository.findByUserId(user.getId());

		if (verificationCode.getVerificationToken() == verifyRequest.getVerifycationToken()) {
			verificationCode.setVerified(true);
			verificationCodeRepository.deleteToken(user.getId());
			verificationCodeRepository.save(verificationCode);	
			accountResponse.setDescription("Xác nhận tài khoản thành công");
		} else {
			accountResponse.setDescription("Mã xác nhận không đúng");
		}

		return accountResponse;
	}

	@Override
	public AccountResponse signIn(SignInRequest signInRequest) {
		// TODO Auto-generated method stub
		AccountResponse accountResponse = new AccountResponse();
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
		 if (authentication.isAuthenticated()) {
			 	accountResponse.setTittle("Đăng nhập thành công!");
			 	accountResponse.setDescription(jwtService.generateToken(signInRequest.getUsername()));
	            return accountResponse;
	        } else {
	            throw new RuntimeException("Đăng nhập thất bại");
	        }
	}

	@Override
	public Long getCurrentUserId() {
		long id = 0;
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    if (authentication != null && authentication.isAuthenticated()) {
		        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		        id = ((User) userPrincipal).getId(); 
		    }
			return id;
	}

}
