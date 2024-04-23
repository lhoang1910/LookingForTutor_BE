package com.ltf.adminservice.controller;

import com.ltf.adminservice.dto.response.AdminResponse;
import com.ltf.adminservice.service.AdminService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/class")
public class TutorClassController {

    @Autowired
    AdminService service;

    @PostMapping("/register-class/{classId}")
    public AdminResponse registerClass(@RequestHeader("loggedInUser") String loggedInUser, @PathVariable long classId) {
        return service.registerClass(loggedInUser, classId);
    }

    @PostMapping("/paid/{classId}")
    public ResponseEntity<String> paid(@PathVariable long classId){
        return ResponseEntity.ok(service.paid(classId));
    }

}
