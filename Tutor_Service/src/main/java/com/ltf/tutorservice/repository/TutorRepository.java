package com.ltf.tutorservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltf.tutorservice.entity.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{

	Optional<Tutor> findByUserId(long id);
	
}
