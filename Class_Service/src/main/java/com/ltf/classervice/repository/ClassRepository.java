package com.ltf.classervice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltf.classervice.entities.Class;

@Repository
public interface ClassRepository extends JpaRepository<com.ltf.classervice.entities.Class, Long>{
    List<Class> findByIsApprovedFalse();

	List<Class> findByIsApprovedTrue();

    List<Class> findAllByStudentId(long id);

    List<Class> findAllByIsPaidTrue();

    List<Class> findAllByIsPaidFalse();

    List<Class> findByIsPaidAndHadTutor(boolean isPaid, boolean hadTutor);

    Class findByClassId(long classId);
}
