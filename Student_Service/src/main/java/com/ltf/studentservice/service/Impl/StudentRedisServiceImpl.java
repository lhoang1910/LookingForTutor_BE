package com.ltf.studentservice.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltf.studentservice.dto.response.StudentProfileResponse;
import com.ltf.studentservice.service.StudentRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class StudentRedisServiceImpl implements StudentRedisService {

    private static final String STUDENT_CACHE_KEY = "StudentCache";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(StudentRedisServiceImpl.class);

    @Autowired
    public StudentRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void clear() {
        boolean isDeleted = redisTemplate.delete(STUDENT_CACHE_KEY);
        logger.info("Đã xóa cache cho danh sách tất cả học sinh: {}", isDeleted ? "thành công" : "thất bại");
    }

    @Override
    public List<StudentProfileResponse> getAllStudents() throws JsonProcessingException {
        if (redisTemplate.hasKey(STUDENT_CACHE_KEY)) {
            logger.info("Cache truy xuất thành công cho danh sách tất cả học sinh");
            String jsonStudents = (String) redisTemplate.opsForValue().get(STUDENT_CACHE_KEY);
            try {
                return objectMapper.readValue(jsonStudents, new TypeReference<List<StudentProfileResponse>>() {});
            } catch (Exception e) {
                logger.error("Lỗi khi chuyển đổi JSON thành đối tượng", e);
                return null;
            }
        } else {
            logger.info("Cache không tìm thấy cho danh sách tất cả học sinh");
            return null;
        }
    }

    @Override
    public void saveAllStudents(List<StudentProfileResponse> studentProfileResponses) throws JsonProcessingException {
        try {
            String jsonStudents = objectMapper.writeValueAsString(studentProfileResponses);
            redisTemplate.opsForValue().set(STUDENT_CACHE_KEY, jsonStudents);
            redisTemplate.expire(STUDENT_CACHE_KEY, Duration.ofHours(1));
            logger.info("Đã lưu vào cache danh sách tất cả học sinh");
        } catch (Exception e) {
            logger.error("Lỗi khi chuyển đổi đối tượng thành JSON", e);
        }
    }
}
