package com.ltf.paymentservice.repository;

import com.ltf.paymentservice.entities.Bill;
import org.bouncycastle.asn1.x500.style.RFC4519Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill findByPaymentCode(String id);

    List<Bill> findAllByUserId(long userId);

    Bill findByPaymentCodeAndUserId(String paymentId, String username);
}
