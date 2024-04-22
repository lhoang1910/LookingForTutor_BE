package com.ltf.paymentservice.controller;

import com.ltf.paymentservice.dto.request.CreateBillRequest;
import com.ltf.paymentservice.entities.Bill;
import com.ltf.paymentservice.service.BillRedisService;
import com.ltf.paymentservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    BillService service;

    @Autowired
    BillRedisService billRedisService;

    @PostMapping("/create")
    ResponseEntity<String> createBill(CreateBillRequest createBillRequest){
        billRedisService.clear();
        return ResponseEntity.ok(service.createBill(createBillRequest));
    }

    @GetMapping("/{id}")
    ResponseEntity<Bill> getBillById(String paymentId, @RequestHeader("loggedInUser") String username){
        return ResponseEntity.ok(service.getBillByPayementId(paymentId, username));
    }

    @GetMapping("/")
    ResponseEntity<List<Bill>> getAllBillOCurrentfUser(@RequestHeader("loggedInUser") String loggedInUser){
        return ResponseEntity.ok(service.getAllBillOCurrentfUser(loggedInUser));
    }

}
