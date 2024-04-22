package com.ltf.classervice.client;

import com.ltf.classervice.dto.request.CreateBillRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PAYMENTSERVICE")
public interface PaymentClient {
    @PostMapping("/api/bill/create")
    String createBill(CreateBillRequest createBillRequest);
}
