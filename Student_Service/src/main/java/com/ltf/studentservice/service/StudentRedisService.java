package com.ltf.studentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ltf.studentservice.dto.response.StudentProfileResponse;

import java.util.List;

public interface StudentRedisService {
    void clear();
    List<StudentProfileResponse> getAllStudents() throws JsonProcessingException;
    void saveAllStudents(List<StudentProfileResponse> studentProfileResponses) throws JsonProcessingException;
}
