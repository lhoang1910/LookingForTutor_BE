package com.ltf.paymentservice.Client;

import com.ltf.paymentservice.dto.response.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USERSERVICE")
public interface UserClient {
    @GetMapping("/api/user/current")
    UserProfile getCurrentUser(@RequestHeader("loggedInUser") String username);
}
