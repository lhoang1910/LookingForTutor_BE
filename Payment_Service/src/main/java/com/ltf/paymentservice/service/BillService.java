package com.ltf.paymentservice.service;

import com.ltf.paymentservice.dto.request.CreateBillRequest;
import com.ltf.paymentservice.entities.Bill;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface BillService {
    String createBill(CreateBillRequest createBillRequest);
    Bill getBill(long id);
    List<Bill> getAllBillOfUser(long userId);
    List<Bill> getAllBillOCurrentfUser(@RequestHeader("loggedInUser") String loggedInUser);
    String paidBill (String paymentId);
    Bill getBillByPayementId(String paymentId, @RequestHeader("loggedInUser") String username);
    List<Bill> getALlBill();
}
