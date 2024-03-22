package com.ltf.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateAccountRequest {
	@NotNull(message = "Họ và tên rỗng")
	@NotEmpty(message = "Họ và tên rỗng")
	@Size(min = 10, max = 50, message = "Vui lòng nhập đúng họ và tên")
	private String fullName;

	@NotNull(message = "Tên đăng nhập rỗng")
	@NotEmpty(message = "Tên đăng nhập rỗng")
	@Size(min = 5, max = 20, message = "username có độ dài từ 5 đến 20 kí tự")
	private String username;

	@NotNull(message = "Mật khẩu rỗng")
	@NotEmpty(message = "Mật khẩu rỗng")
	@Size(min = 6, max = 30, message = "Mật khẩu từ 6-30 ký tự")
	private String password;

	@NotNull(message = "Mật khẩu rỗng")
	@NotEmpty(message = "Mật khẩu rỗng")
	@Size(min = 6, max = 30, message = "Mật khẩu từ 6-30 ký tự")
	private String confirmPassword;

	@NotNull(message = "Email rỗng")
	@NotEmpty(message = "Email rỗng")
	@Size(min = 5, max = 30, message = "Email từ 5-30 ký tự")
	@Email(message = "Email không hợp lệ")
	private String email;

	@NotNull(message = "Địa chỉ rỗng")
	@NotEmpty(message = "Địa chỉ rỗng")
	private String address;

	@NotNull(message = "Số điện thoại rỗng")
	@NotEmpty(message = "Số điện thoại rỗng")
	@Size(min = 10, max = 12, message = "Vui lòng nhập đúng số điện thoại")
	private String number;

	private String role;

	public CreateAccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateAccountRequest(
			@NotNull(message = "Họ và tên rỗng") @NotEmpty(message = "Họ và tên rỗng") @Size(min = 10, max = 50, message = "Vui lòng nhập đúng họ và tên") String fullName,
			@NotNull(message = "Tên đăng nhập rỗng") @NotEmpty(message = "Tên đăng nhập rỗng") @Size(min = 5, max = 20, message = "username có độ dài từ 5 đến 20 kí tự") String username,
			@NotNull(message = "Mật khẩu rỗng") @NotEmpty(message = "Mật khẩu rỗng") @Size(min = 6, max = 30, message = "Mật khẩu từ 6-30 ký tự") String password,
			@NotNull(message = "Mật khẩu rỗng") @NotEmpty(message = "Mật khẩu rỗng") @Size(min = 6, max = 30, message = "Mật khẩu từ 6-30 ký tự") String confirmPassword,
			@NotNull(message = "Email rỗng") @NotEmpty(message = "Email rỗng") @Size(min = 5, max = 30, message = "Email từ 5-30 ký tự") @Email(message = "Email không hợp lệ") String email,
			@NotNull(message = "Địa chỉ rỗng") @NotEmpty(message = "Địa chỉ rỗng") String address,
			@NotNull(message = "Số điện thoại rỗng") @NotEmpty(message = "Số điện thoại rỗng") @Size(min = 10, max = 12, message = "Vui lòng nhập đúng số điện thoại") String number,
			String role) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.address = address;
		this.number = number;
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}