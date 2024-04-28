package com.ltf.message.service.Impl;

import com.ltf.message.client.AdminClient;
import com.ltf.message.client.ClassClient;
import com.ltf.message.dto.response.ListChat;
import com.ltf.message.entities.ChatMessage;
import com.ltf.message.repository.ChatMessageRepository;
import com.ltf.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired
    AdminClient adminClient;

    @Autowired
    ClassClient classClient;

    @Override
    public List<ListChat> getListChatBox(String loggedInUser) {
        List<ListChat> listChatsForChatBox = new ArrayList<>();
        List<ListChat> listChatsTutor = adminClient.getListChatForTutor(loggedInUser);
        if (listChatsTutor != null){
            listChatsForChatBox.addAll(listChatsTutor);
        } else {
            List<ListChat> listChatsStudent = classClient.getListChatForStudent(loggedInUser);
            listChatsForChatBox.addAll(listChatsStudent);
        }
        return listChatsForChatBox;
    }

    @Override
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return null;
    }
}
