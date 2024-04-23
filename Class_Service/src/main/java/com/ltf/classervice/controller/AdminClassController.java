package com.ltf.classervice.controller;

import com.ltf.classervice.dto.response.ClassResponse;
import com.ltf.classervice.entities.Class;
import com.ltf.classervice.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/class")
public class AdminClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/all")
    public ResponseEntity<List<Class>> getAllClasses() {
        return ResponseEntity.ok(classService.classes());
    }

    @GetMapping("/unapproved")
    public ResponseEntity<List<Class>> getUnapprovedClasses() {
        return ResponseEntity.ok(classService.getUnapprovedClasses());
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Class>> getApprovedClasses() {
        return ResponseEntity.ok(classService.getApprovedClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable long id) {
        return ResponseEntity.ok(classService.getClassById(id));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Class>> getStudentClassesById(@PathVariable long id) {
        return ResponseEntity.ok(classService.studentClasseseById(id));
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<ClassResponse> approveClass(@PathVariable long id) {
        return ResponseEntity.ok(classService.approveClass(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClassResponse> deleteClass(@PathVariable long id) {
        return ResponseEntity.ok(classService.deleteClass(id));
    }

    @GetMapping("/unpaid")
    public ResponseEntity<List<Class>> getUnpaidClasses() {
        return ResponseEntity.ok(classService.getUnPaid());
    }

    @GetMapping("/paid")
    public ResponseEntity<List<Class>> getPaidClasses() {
        return ResponseEntity.ok(classService.getPaid());
    }

    @GetMapping("/had-tutor")
    public ResponseEntity<List<Class>> getClassesHadTutor(){
        return ResponseEntity.ok(classService.getClassesHadTutor());
    }
}
