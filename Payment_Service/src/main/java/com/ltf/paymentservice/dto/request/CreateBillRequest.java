package com.ltf.paymentservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBillRequest {
    private String paymentCode;
    private long userId;
    private String description;
    private long amountOfMoney;
    private LocalDateTime timePaid;
    private boolean isPaid;
}
