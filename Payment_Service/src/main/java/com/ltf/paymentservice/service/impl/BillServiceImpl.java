package com.ltf.paymentservice.service.impl;

import com.ltf.paymentservice.Client.ClassClient;
import com.ltf.paymentservice.Client.UserClient;
import com.ltf.paymentservice.dto.request.CreateBillRequest;
import com.ltf.paymentservice.dto.response.UserProfile;
import com.ltf.paymentservice.entities.Bill;
import com.ltf.paymentservice.repository.BillRepository;
import com.ltf.paymentservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository repository;

    @Autowired
    ClassClient classClient;

    @Autowired
    UserClient userClient;

    @Override
    public String createBill(CreateBillRequest createBillRequest) {
        Bill bill = new Bill();
        bill.setAmountOfMoney(createBillRequest.getAmountOfMoney());
        bill.setDescription(createBillRequest.getDescription());
        bill.setPaymentCode(createBillRequest.getPaymentCode());
        bill.setPaid(false);
        bill.setUserId(createBillRequest.getUserId());
        repository.save(bill);
        return "Tạo bill thành công";
    }

    @Override
    public Bill getBill(long id) {
        Optional<Bill> bill = repository.findById(id);
        return bill.get();
    }

    @Override
    public List<Bill> getAllBillOfUser(long userId) {
        List<Bill> bills = repository.findAllByUserId(userId);
        return bills;
    }

    @Override
    public List<Bill> getAllBillOCurrentfUser(String loggedInUser) {
        UserProfile userProfile = userClient.getCurrentUser(loggedInUser);
        List<Bill> bills = repository.findAllByUserId(userProfile.getId());
        return bills;
    }

    @Override
    public String paidBill(String paymentId) {
        Bill bill = repository.findByPaymentCode(paymentId);
        bill.setPaid(true);
        repository.save(bill);
        if (paymentId.startsWith("TUT")){
            long tutorId = Long.parseLong(paymentId.substring(3));
            classClient.addTutor(Long.parseLong(bill.getDescription()));
        }
        return "Thanh toán thành công";
    }

    @Override
    public Bill getBillByPayementId(String paymentId, String username) {
        Bill bill = repository.findByPaymentCodeAndUserId(paymentId, username);
        return bill;
    }

    @Override
    public List<Bill> getALlBill() {
        List<Bill> bills = repository.findAll();
        return bills;
    }

}
