package com.ltf.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ltf.paymentservice.entities.Bill;

import java.util.List;

public interface BillRedisService {
    void clear();
    List<Bill> getAllBills() throws JsonProcessingException;
    void saveAllBills(List<Bill> bills) throws JsonProcessingException;
}
