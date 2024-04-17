package com.ltf.tutorservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltf.tutorservice.entity.Image;
import com.ltf.tutorservice.entity.Tutor;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

	Optional<Image> findByTutorId(long id);

	Optional<Image> findByTutor(Optional<Tutor> optionalTutor);

	Optional<Image> findByTutor(Tutor tutor);
	
}
