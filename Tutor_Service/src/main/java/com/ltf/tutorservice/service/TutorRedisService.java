package com.ltf.tutorservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ltf.tutorservice.dto.response.TutorProfileResponse;

import java.util.List;

public interface TutorRedisService {
    void clear();
    List<TutorProfileResponse> getAllTutors() throws JsonProcessingException;
    void saveAllTutors(List<TutorProfileResponse> tutors) throws JsonProcessingException;
}
