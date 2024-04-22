package com.ltf.studentservice.client;

import com.ltf.studentservice.dto.response.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USERSERVICE")
public interface UserClient {

    @GetMapping("/api/user/current")
    UserProfile getCurrentUser(@RequestHeader("loggedInUser") String username);

    @GetMapping("/api/user/{id}")
    UserProfile getUserInfoById(@PathVariable long id);
}
