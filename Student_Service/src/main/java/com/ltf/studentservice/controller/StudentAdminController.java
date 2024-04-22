package com.ltf.studentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ltf.studentservice.dto.response.StudentProfileResponse;
import com.ltf.studentservice.entities.Student;
import com.ltf.studentservice.service.StudentRedisService;
import com.ltf.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/student")
public class StudentAdminController {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentRedisService studentRedisService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentProfileResponse>> getAllStudent() throws JsonProcessingException {
        List<StudentProfileResponse> students = studentRedisService.getAllStudents();
        if (students != null){
            return ResponseEntity.ok(students);
        } else {
            students = studentService.listStudent();
            if (students != null){
                try {
                    studentRedisService.saveAllStudents(students);
                } catch (JsonProcessingException e){
                    e.printStackTrace();
                }
                return ResponseEntity.ok(students);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileResponse> getStudent(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }


}
