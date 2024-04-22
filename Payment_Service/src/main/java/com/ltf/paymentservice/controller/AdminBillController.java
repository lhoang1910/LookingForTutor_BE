package com.ltf.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ltf.paymentservice.entities.Bill;
import com.ltf.paymentservice.service.BillRedisService;
import com.ltf.paymentservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/bill")
public class AdminBillController {

    @Autowired
    BillService service;

    @Autowired
    BillRedisService billRedisService;

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable long id){
        return ResponseEntity.ok(service.getBill(id));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Bill>> getAllBillOfUser(@PathVariable long userId){
        return ResponseEntity.ok(service.getAllBillOfUser(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bill>> getALlBill() throws JsonProcessingException {
        List<Bill> bills = billRedisService.getAllBills();
        if (bills != null) {
            return ResponseEntity.ok(bills);
        } else {
            bills = service.getALlBill();
            if (bills != null) {
                try {
                    billRedisService.saveAllBills(bills);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return ResponseEntity.ok(bills);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

}
