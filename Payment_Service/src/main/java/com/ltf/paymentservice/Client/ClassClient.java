package com.ltf.paymentservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "CLASSSERVICE")
public interface ClassClient {
//    @GetMapping("/api/info/all")
//    public List<ClassInfoResponse> getAllClassInfo();
//
//    @GetMapping("/api/class/info/{id}")
//    public ClassInfoResponse getClassInfoById(@PathVariable long id);

    @PostMapping("/add-tutor")
    String addTutor(@PathVariable long id);
}

