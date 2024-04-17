package com.ltf.classervice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltf.classervice.entities.Class;

@Repository
public interface ClassRepository extends JpaRepository<com.ltf.classervice.entities.Class, Long>{
	List<Class> findByHadTutorFalse();

	List<Class> findByHadTutorTrue();

	Class findByIdAndHadTutorFalse(long id);

}
