package com.ltf.paymentservice.Client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ADMINSERVICE")
public interface AdminClient {
    @PostMapping("/paid/{classId}")
    String paid(@PathVariable long classId);
}
