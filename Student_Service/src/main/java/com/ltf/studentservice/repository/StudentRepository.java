package com.ltf.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltf.studentservice.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
