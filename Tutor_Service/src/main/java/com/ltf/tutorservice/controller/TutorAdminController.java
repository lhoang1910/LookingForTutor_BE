package com.ltf.tutorservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ltf.tutorservice.dto.response.TutorProfileResponse;
import com.ltf.tutorservice.entity.Tutor;
import com.ltf.tutorservice.service.TutorRedisService;
import com.ltf.tutorservice.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/tutor")
public class TutorAdminController {

    @Autowired
    TutorService tutorService;

    @Autowired
    TutorRedisService tutorRedisService;

    @GetMapping("/all")
    public ResponseEntity<List<TutorProfileResponse>> getAllTutor() throws JsonProcessingException {
        List<TutorProfileResponse> tutors = tutorRedisService.getAllTutors();
        if (tutors != null) {
            return ResponseEntity.ok(tutors);
        } else {
            tutors = tutorService.getALlTutor();
            if (tutors != null) {
                try {
                    tutorRedisService.saveAllTutors(tutors);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return ResponseEntity.ok(tutors);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorProfileResponse> tutorProfileResponse(@PathVariable long id) {
        return ResponseEntity.ok(tutorService.getTutorInfoById(id));
    }
}
