package com.ltf.paymentservice.controller;

import com.ltf.paymentservice.Client.AdminClient;
import com.ltf.paymentservice.dto.response.ClassFee;
import com.ltf.paymentservice.service.VNPayService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private AdminClient adminClient;

	@Autowired
	private VNPayService vnpayService;

	@GetMapping("/create-qr/{classId}")
	public String createPaymentQRCode(@PathVariable long classId, HttpServletRequest request) {

		ClassFee classFee = adminClient.admissionClassFee(classId);
		double totalPrice = classFee.getTotalPrice();

		String orderInfo = "Thanh toán học phí cho lớp học ID: " + classId;
		String returnUrl = request.getRequestURL().toString();

		String paymentUrl = vnpayService.createOrder((int) totalPrice, orderInfo, returnUrl);

		return paymentUrl;
	}

	@GetMapping("/vnpay-payment")
	public String handleVNPayReturn(HttpServletRequest request) {
		int paymentStatus = vnpayService.orderReturn(request);

		if (paymentStatus == 1) {
			return "Payment successful";
		} else {
			return "Payment failed";
		}
	}
}
