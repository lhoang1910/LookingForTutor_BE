package com.ltf.adminservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltf.adminservice.dto.response.TutorClassInfoResponse;
import com.ltf.adminservice.service.AdminRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;


@Service
public class AdminRedisServiceImpl {
    private static final String UNAPPROVED_CLASSES_CACHE_KEY = "UnapprovedClassesCache";
    private static final String APPROVED_CLASSES_CACHE_KEY = "ApprovedClassesCache";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdminRedisService.class);

    @Autowired
    public AdminRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void clear() {
        boolean isDeleted = redisTemplate.delete(UNAPPROVED_CLASSES_CACHE_KEY);
        isDeleted = redisTemplate.delete(APPROVED_CLASSES_CACHE_KEY);
        logger.info("Cache cleared for unapproved and approved classes: {}", isDeleted ? "successful" : "failed");
    }

    public List<TutorClassInfoResponse> getAllUnapprovedClasses() throws JsonProcessingException {
        return getDataFromCache(UNAPPROVED_CLASSES_CACHE_KEY);
    }

    public List<TutorClassInfoResponse> getAllApprovedClasses() throws JsonProcessingException {
        return getDataFromCache(APPROVED_CLASSES_CACHE_KEY);
    }

    private List<TutorClassInfoResponse> getDataFromCache(String cacheKey) throws JsonProcessingException {
        if (redisTemplate.hasKey(cacheKey)) {
            logger.info("Cache hit for key: {}", cacheKey);
            String jsonData = (String) redisTemplate.opsForValue().get(cacheKey);
            return objectMapper.readValue(jsonData, new TypeReference<List<TutorClassInfoResponse>>() {});
        } else {
            logger.info("Cache miss for key: {}", cacheKey);
            return null;
        }
    }

    public void saveAllUnapprovedClasses(List<TutorClassInfoResponse> unapprovedClasses) throws JsonProcessingException {

        saveDataToCache(UNAPPROVED_CLASSES_CACHE_KEY, unapprovedClasses);
    }

    public void saveAllApprovedClasses(List<TutorClassInfoResponse> approvedClasses) throws JsonProcessingException {
        saveDataToCache(APPROVED_CLASSES_CACHE_KEY, approvedClasses);
    }

    private void saveDataToCache(String cacheKey, List<TutorClassInfoResponse> data) throws JsonProcessingException {
        String jsonData = objectMapper.writeValueAsString(data);
        redisTemplate.opsForValue().set(cacheKey, jsonData);
        redisTemplate.expire(cacheKey, Duration.ofHours(1)); // Set cache expiry time (e.g., 1 hour)
        logger.info("Data saved to cache with key: {}", cacheKey);
    }
}
