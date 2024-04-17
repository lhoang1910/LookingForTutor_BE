package com.ltf.userservice.dto.request;

import com.ltf.userservice.entities.Role;

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

	@NotNull(message = "Ngày sinh rỗng")
	@NotEmpty(message = "Ngày sinh rỗng")
	private String dateOfBirth;

	@NotNull(message = "Số điện thoại rỗng")
	@NotEmpty(message = "Số điện thoại rỗng")
	@Size(min = 10, max = 12, message = "Vui lòng nhập đúng số điện thoại")
	private String number;

	@NotNull(message = "Không được để trống giới tính")
	@NotEmpty(message = "Không được để trống giới tính")
	private String sex;

	private Role role;

	public CreateAccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public CreateAccountRequest(
			@NotNull(message = "Họ và tên rỗng") @NotEmpty(message = "Họ và tên rỗng") @Size(min = 10, max = 50, message = "Vui lòng nhập đúng họ và tên") String fullName,
			@NotNull(message = "Tên đăng nhập rỗng") @NotEmpty(message = "Tên đăng nhập rỗng") @Size(min = 5, max = 20, message = "username có độ dài từ 5 đến 20 kí tự") String username,
			@NotNull(message = "Mật khẩu rỗng") @NotEmpty(message = "Mật khẩu rỗng") @Size(min = 6, max = 30, message = "Mật khẩu từ 6-30 ký tự") String password,
			@NotNull(message = "Mật khẩu rỗng") @NotEmpty(message = "Mật khẩu rỗng") @Size(min = 6, max = 30, message = "Mật khẩu từ 6-30 ký tự") String confirmPassword,
			@NotNull(message = "Email rỗng") @NotEmpty(message = "Email rỗng") @Size(min = 5, max = 30, message = "Email từ 5-30 ký tự") @Email(message = "Email không hợp lệ") String email,
			@NotNull(message = "Ngày sinh rỗng") @NotEmpty(message = "Ngày sinh rỗng") String dateOfBirth,
			@NotNull(message = "Số điện thoại rỗng") @NotEmpty(message = "Số điện thoại rỗng") @Size(min = 10, max = 12, message = "Vui lòng nhập đúng số điện thoại") String number,
			@NotNull(message = "Không được để trống giới tính") @NotEmpty(message = "Không được để trống giới tính") String sex,
			Role role) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.number = number;
		this.sex = sex;
		this.role = role;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
