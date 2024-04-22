package com.ltf.userservice.dto.request;

import com.ltf.userservice.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
