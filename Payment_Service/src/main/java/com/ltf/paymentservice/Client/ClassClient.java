package com.ltf.paymentservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "CLASSSERVICE")
public interface ClassClient {

    @PostMapping("/api/class/add-tutor/{id}")
    String addTutor(@PathVariable long id);

    @PostMapping("/api/class/paid/{id}")
    ResponseEntity<String> paid(@PathVariable long id);
}

