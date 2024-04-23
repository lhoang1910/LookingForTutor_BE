package com.ltf.adminservice.service;

import com.ltf.adminservice.dto.response.TutorClassInfoResponse;
import com.ltf.adminservice.entities.ClassManagerment;

import java.util.List;

public interface AdminRedisService {
    List<ClassManagerment> saveAllClassManagerment();
    List<ClassManagerment> getAllClassManagerment();
    void clear();
}
