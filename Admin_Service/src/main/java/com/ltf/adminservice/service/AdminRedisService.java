package com.ltf.adminservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ltf.adminservice.entities.ClassManagerment;

import java.util.List;

public interface AdminRedisService {
    void saveAllClassManagerment(List<ClassManagerment> classManagerments) throws JsonProcessingException;
    List<ClassManagerment> getAllClassManagerment() throws JsonProcessingException;
    void clear();

}
