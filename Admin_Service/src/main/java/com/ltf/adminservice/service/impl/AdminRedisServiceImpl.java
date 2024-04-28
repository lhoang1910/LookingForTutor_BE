package com.ltf.adminservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltf.adminservice.entities.ClassManagerment;
import com.ltf.adminservice.service.AdminRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;


@Service
public class AdminRedisServiceImpl implements  AdminRedisService{
    private static final String CLASSMANAGERMENT_CACHE_KEY = "AllClassManagermentCache";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdminRedisService.class);

    @Autowired
    public AdminRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void clear() {
        boolean isDeleted = redisTemplate.delete(CLASSMANAGERMENT_CACHE_KEY);
        logger.info("Đã xóa cache cho danh sách tất cả class mamangerment: {}", isDeleted ? "thành công" : "thất bại");
    }

    @Override
    public List<ClassManagerment> getAllClassManagerment() throws JsonProcessingException {
        if (redisTemplate.hasKey(CLASSMANAGERMENT_CACHE_KEY)) {
            logger.info("Cache truy xuất thành công cho danh sách tất cả class mamnagerment");
            String classSManagermentJson = (String) redisTemplate.opsForValue().get(CLASSMANAGERMENT_CACHE_KEY);
            try {
                return objectMapper.readValue(classSManagermentJson, new TypeReference<List<ClassManagerment>>() {});
            } catch (Exception e) {
                logger.error("Lỗi khi chuyển đổi JSON thành đối tượng", e);
                return null;
            }
        } else {
            logger.info("Cache không tìm thấy cho danh sách tất cả class managermemnt");
            return null;
        }
    }

    @Override
    public void saveAllClassManagerment(List<ClassManagerment> classManagerments) throws JsonProcessingException {
        try {
            String jsonClassManagerment = objectMapper.writeValueAsString(classManagerments);
            redisTemplate.opsForValue().set(CLASSMANAGERMENT_CACHE_KEY, jsonClassManagerment);
            redisTemplate.expire(CLASSMANAGERMENT_CACHE_KEY, Duration.ofHours(1));
            logger.info("Đã lưu vào cache danh sách tất cả classmanagerment");
        } catch (Exception e) {
            logger.error("Lỗi khi chuyển đổi đối tượng thành JSON", e);
        }
    }


}
