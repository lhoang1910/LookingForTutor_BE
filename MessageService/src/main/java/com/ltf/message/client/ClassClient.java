package com.ltf.message.client;

import com.ltf.message.dto.response.ListChat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "CLASSSERVICE")
public interface ClassClient {
    @GetMapping("/chat-list")
    public List<ListChat> getListChatForStudent(@RequestHeader("loggedInUser") String loggedInUser);
}

