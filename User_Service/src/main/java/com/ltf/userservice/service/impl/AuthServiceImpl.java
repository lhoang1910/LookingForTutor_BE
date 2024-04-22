package com.ltf.userservice.service.impl;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltf.userservice.dto.request.CreateAccountRequest;
import com.ltf.userservice.dto.request.ResetPasswordRequest;
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

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
					user.setDateOfBirth(createAccountRequest.getDateOfBirth());
					user.setEmail(createAccountRequest.getEmail());
					user.setNumber(createAccountRequest.getNumber());
					user.setUsername(createAccountRequest.getUsername());
					user.setSex(createAccountRequest.getSex());
					user.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));

					// Thiết lập Role
					if (createAccountRequest.getRole().equals(Role.ROLE_TUTOR)) {
						user.setRole(Role.ROLE_TUTOR);
					} else if (createAccountRequest.getRole().equals(Role.ROLE_STUDENT)){
						user.setRole(Role.ROLE_STUDENT);
					} else {
						user.setRole(Role.ROLE_ADMIN);
					}

					// Lưu User vào cơ sở dữ liệu
					User savedUser = userRepository.save(user);

					sendVerificationCode(savedUser.getEmail());

					accountResponse.setDescription("Đăng ký thành công, vui lòng xác thực tài khoản");
				}
			}
		}
		return accountResponse;
	}

	public static int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(900000) + 100000;
	}

	private void scheduleDeletion(long id) {
		scheduler.schedule(() -> {
			// Xóa các mã xác nhận đã hết hạn sau 5 phút
			java.util.Date currentTime = new java.util.Date();
			java.util.Date fiveMinutesAgo = new java.util.Date(currentTime.getTime() + 5 * 60 * 1000);
			verificationCodeRepository.deleteById(id);
		}, 5, TimeUnit.MINUTES);
	}

	@Override
	public AccountResponse sendVerificationCode(String email) {
		System.out.println(email);
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản với email này"));
		VerificationCode code = verificationCodeRepository.findByUser(user);
		if (code == null) {
			code = new VerificationCode();
			code.setUser(user);
		}
		java.util.Date date = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
		code.setVerified(false);
		code.setVerificationToken(randomNumber());
		code.setExp(date);
		verificationCodeRepository.save(code);
		scheduleDeletion(code.getId());

		sendVerificationEmail(user, code);
		System.out.println(code);
		return new AccountResponse("Mã xác nhận đã được gửi tới email của bạn",
				"Vui lòng kiểm tra, mã chỉ có hiệu lực 5p");
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

		VerificationCode verificationCode = verificationCodeRepository.findByUser(user);

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
	    AccountResponse accountResponse = new AccountResponse();
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
	    if (authentication.isAuthenticated()) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String userRole = userDetails.getAuthorities().isEmpty() ? null : userDetails.getAuthorities().iterator().next().getAuthority();
	        String token = jwtService.generateToken(signInRequest.getUsername(), userRole);

	        accountResponse.setTittle("Đăng nhập thành công!");
	        accountResponse.setDescription(token);
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

	@Override
	public AccountResponse forgetPassword(ResetPasswordRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new NotFoundException("Người dùng không tồn tại"));
		VerificationCode code = verificationCodeRepository.findByUser(user);
		if (request.getToken() == code.getVerificationToken()) {
			if (request.getNewPassWord().equals(request.getConfirmNewPassword())) {
				user.setPassword(passwordEncoder.encode(request.getNewPassWord()));
				userRepository.save(user);
				return new AccountResponse("Thành công", "Đổi mật khẩu thành công");
			} else {
				return new AccountResponse("Thất bại", "Vui lòng kiểm tra lại mật khẩu mới");
			}
		}
		return new AccountResponse("Thất bại", "Mã xác nhận không đúng");
	}

}
