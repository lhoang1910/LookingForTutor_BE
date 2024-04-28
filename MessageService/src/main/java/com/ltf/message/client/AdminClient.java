package com.ltf.message.client;

import com.ltf.message.dto.response.ListChat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name="ADMINSERVICE")
public interface AdminClient {
    @GetMapping("/chat-list")
    public List<ListChat> getListChatForTutor(@RequestHeader("loggedInUser") String loggedInUser);
}
