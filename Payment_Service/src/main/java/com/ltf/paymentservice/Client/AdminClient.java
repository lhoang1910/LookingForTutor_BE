package com.ltf.paymentservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ltf.paymentservice.dto.response.ClassFee;

@FeignClient(name = "ADMINSERVICE")
public interface AdminClient {
	@GetMapping("/api/admin/class-fee/{id}")
	ClassFee admissionClassFee(@PathVariable long id);
}
