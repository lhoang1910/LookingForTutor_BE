package com.ltf.adminservice.controller;

import com.ltf.adminservice.dto.response.AdminResponse;
import com.ltf.adminservice.dto.response.ListChatTutor;
import com.ltf.adminservice.dto.response.TutorProfileResponse;
import com.ltf.adminservice.service.AdminService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/chat-list")
    public ResponseEntity<List<ListChatTutor>> getListChatForTutor(@RequestHeader("loggedInUser") String loggedInUser){
        return ResponseEntity.ok(service.listChatForTutor(loggedInUser));
    }

    @GetMapping("/tutor/{classId}")
    public ResponseEntity<TutorProfileResponse> getTutorByClassId(@PathVariable long classId){
        return ResponseEntity.ok(service.getTutorResponseByClassId(classId));
    }

}
