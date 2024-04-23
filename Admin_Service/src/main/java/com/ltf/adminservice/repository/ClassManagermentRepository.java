package com.ltf.adminservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltf.adminservice.entities.ClassManagerment;

@Repository
public interface ClassManagermentRepository extends JpaRepository<ClassManagerment, Long>{

	List<ClassManagerment> findByApprovedFalse();

	ClassManagerment findByTutorIdAndClassId(long tutorId, long classId);

    List<ClassManagerment> findByApprovedTrue();

	ClassManagerment findByClassId(long classId);

    List<ClassManagerment> findAllByApproved(boolean b);

	List<ClassManagerment> findAllByPaid(boolean b);

	List<ClassManagerment> findAllByTutorId(long tutorId);
}
