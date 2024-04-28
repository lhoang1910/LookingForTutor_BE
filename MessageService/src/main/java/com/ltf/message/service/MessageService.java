package com.ltf.message.service;

import com.ltf.message.dto.response.ListChat;
import com.ltf.message.entities.ChatMessage;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface MessageService {
    ChatMessage sendMessage(ChatMessage chatMessage);
    List<ListChat> getListChatBox(@RequestHeader("loggedInUser") String loggedInUser);
}
