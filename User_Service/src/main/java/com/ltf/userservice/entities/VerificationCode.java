package com.ltf.userservice.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "verification_code")
public class VerificationCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "verification_token")
	private Integer verificationToken;

	@Column(name = "is_verified")
	private boolean isVerified;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exp", nullable = false, updatable = false)
	@CreationTimestamp
	private Date exp;

	public VerificationCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VerificationCode(Long id, Integer verificationToken, boolean isVerified, User user, Date exp) {
		super();
		this.id = id;
		this.verificationToken = verificationToken;
		this.isVerified = isVerified;
		this.user = user;
		this.exp = exp;
	}

	public Date getExp() {
		return exp;
	}

	public void setExp(Date exp) {
		this.exp = exp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(Integer verificationToken) {
		this.verificationToken = verificationToken;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
