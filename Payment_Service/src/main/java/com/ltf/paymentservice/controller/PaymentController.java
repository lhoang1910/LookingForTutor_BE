package com.ltf.paymentservice.controller;

import com.ltf.paymentservice.entities.Bill;
import com.ltf.paymentservice.repository.BillRepository;
import com.ltf.paymentservice.service.BillService;
import com.ltf.paymentservice.service.VNPayService;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private VNPayService vnpayService;

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private BillService service;

	@GetMapping("/create-qr/{billId}")
	public void createPaymentQRCode(@PathVariable long billId, HttpServletResponse response, HttpServletRequest request) throws IOException {
		Bill bill = billRepository.findById(billId)
				.orElseThrow(() -> new NotFoundException("Không tìm thấy hoá đơn với id này"));

		if (bill == null) {
			response.sendRedirect("/error-page");
			return;
		}

		// Lưu paymentCode vào session
		HttpSession session = request.getSession();
		session.setAttribute("paymentCode", bill.getPaymentCode());

		String orderInfo = "Thanh toán học phí cho lớp học ID: " + bill.getId();
		String returnUrl = request.getRequestURL().toString().replace("/create-qr/" + billId, "/vnpay-payment");

		String paymentUrl = vnpayService.createOrder(bill.getAmountOfMoney(), orderInfo, returnUrl);

		response.sendRedirect(paymentUrl);
	}


	@GetMapping("/vnpay-payment")
	public String handleVNPayReturn(HttpServletRequest request) {
		// Lấy paymentCode từ session
		HttpSession session = request.getSession();
		String paymentCode = (String) session.getAttribute("paymentCode");
		if (paymentCode == null || paymentCode.isEmpty()) {
			return "Không tìm thấy thông tin thanh toán";
		}

		int paymentStatus = vnpayService.orderReturn(request);

		if (paymentStatus == 1) {
			String result = service.paidBill(paymentCode);
			return "Thanh toán thành công. " + result;
		} else {
			return "Thanh toán thất bại";
		}
	}

}
