package com.example.service;

import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Message;

public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Message createMessage(Message message){
        final boolean IS_EMPTY_MESSAGE = message.getMessageText().length() == 0;
        
        return null;
    }
}
