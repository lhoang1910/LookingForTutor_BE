package com.ltf.paymentservice.controller;

import com.ltf.paymentservice.entities.Bill;
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

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable long id){
        return ResponseEntity.ok(service.getBill(id));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Bill>> getAllBillOfUser(@PathVariable long userId){
        return ResponseEntity.ok(service.getAllBillOfUser(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bill>> getALlBill(){
        return ResponseEntity.ok(service.getALlBill());
    }

}
