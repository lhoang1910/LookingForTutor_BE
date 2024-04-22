package com.ltf.paymentservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltf.paymentservice.entities.Bill;
import com.ltf.paymentservice.service.BillRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class BillRedisServiceImpl implements BillRedisService {

    private static final String BILL_CACHE_KEY = "BillCache";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(BillRedisService.class);

    public BillRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void clear() {
        boolean isDeleted = redisTemplate.delete(BILL_CACHE_KEY);
        logger.info("Đã xóa cache cho danh sách tất cả hóa đơn: {}", isDeleted ? "thành công" : "thất bại");
    }

    @Override
    public List<Bill> getAllBills() throws JsonProcessingException {
        if (redisTemplate.hasKey(BILL_CACHE_KEY)) {
            logger.info("Cache truy xuất thành công cho danh sách tất cả hóa đơn");
            String jsonBills = (String) redisTemplate.opsForValue().get(BILL_CACHE_KEY);
            try {
                return objectMapper.readValue(jsonBills, new TypeReference<List<Bill>>() {});
            } catch (Exception e) {
                logger.error("Lỗi khi chuyển đổi JSON thành đối tượng", e);
                return null;
            }
        } else {
            logger.info("Cache không tìm thấy cho danh sách tất cả hóa đơn");
            return null;
        }
    }

    @Override
    public void saveAllBills(List<Bill> bills) throws JsonProcessingException {
        try {
            String jsonBills = objectMapper.writeValueAsString(bills);
            redisTemplate.opsForValue().set(BILL_CACHE_KEY, jsonBills);
            redisTemplate.expire(BILL_CACHE_KEY, Duration.ofHours(1));
            logger.info("Đã lưu vào cache danh sách tất cả hóa đơn");
        } catch (Exception e) {
            logger.error("Lỗi khi chuyển đổi đối tượng thành JSON", e);
        }
    }
}
