package com.ltf.message.controller;

import com.ltf.message.dto.response.ListChat;
import com.ltf.message.entities.ChatMessage;
import com.ltf.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class ChatController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public ResponseEntity<List<ListChat>> getListChatForChatBox(@RequestHeader("loggedInUser") String loggedInUser){
        return ResponseEntity.ok(messageService.getListChatBox(loggedInUser));
    }



}
