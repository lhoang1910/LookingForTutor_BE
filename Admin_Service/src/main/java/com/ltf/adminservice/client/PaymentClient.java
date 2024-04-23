package com.ltf.adminservice.client;

import com.ltf.adminservice.dto.request.CreateBillRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PAYMENTSERVICE")
public interface PaymentClient {
    @PostMapping("/api/bill/create")
    String createBill(CreateBillRequest createBillRequest);
}
