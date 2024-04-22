package com.ltf.tutorservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltf.tutorservice.dto.response.TutorProfileResponse;
import com.ltf.tutorservice.service.TutorRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class TutorRedisServiceImpl implements TutorRedisService {

    private static final String TUTOR_CACHE_KEY = "TutorCache";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(TutorRedisService.class);

    @Autowired
    public TutorRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void clear() {
        boolean isDeleted = redisTemplate.delete(TUTOR_CACHE_KEY);
        logger.info("Đã xóa cache cho danh sách tất cả gia sư: {}", isDeleted ? "thành công" : "thất bại");
    }

    @Override
    public List<TutorProfileResponse> getAllTutors() throws JsonProcessingException {
        if (redisTemplate.hasKey(TUTOR_CACHE_KEY)) {
            logger.info("Cache truy xuất thành công cho danh sách tất cả gia sư");
            String jsonTutors = (String) redisTemplate.opsForValue().get(TUTOR_CACHE_KEY);
            try {
                return objectMapper.readValue(jsonTutors, new TypeReference<List<TutorProfileResponse>>() {});
            } catch (Exception e) {
                logger.error("Lỗi khi chuyển đổi JSON thành đối tượng", e);
                return null;
            }
        } else {
            logger.info("Cache không tìm thấy cho danh sách tất cả gia sư");
            return null;
        }
    }

    @Override
    public void saveAllTutors(List<TutorProfileResponse> tutors) throws JsonProcessingException {
        try {
            String jsonTutors = objectMapper.writeValueAsString(tutors);
            redisTemplate.opsForValue().set(TUTOR_CACHE_KEY, jsonTutors);
            redisTemplate.expire(TUTOR_CACHE_KEY, Duration.ofHours(1));
            logger.info("Đã lưu vào cache danh sách tất cả gia sư");
        } catch (Exception e) {
            logger.error("Lỗi khi chuyển đổi đối tượng thành JSON", e);
        }
    }
}
