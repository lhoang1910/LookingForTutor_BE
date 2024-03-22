package com.ltf.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ltf.userservice.entities.VerificationCode;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
	VerificationCode findByUserId(Long id);

	@Modifying
	@Query("UPDATE VerificationCode vc SET vc.verificationToken = null WHERE vc.user.id = :userId")
	void deleteToken(@Param("userId") Long userId);
}
