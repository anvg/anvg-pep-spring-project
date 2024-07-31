package com.example.service;

import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Message;

public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Message createMessage(Message message){
        
        final boolean MESSAGE_HAS_CONTENT = message.getMessageText().length() > 0;
        final boolean MESSAGE_BELOW_CHARACTER_LIMIT = 
        message.getMessageText().length() <= 255;

        if(MESSAGE_HAS_CONTENT && MESSAGE_BELOW_CHARACTER_LIMIT){
            ;
        }else{
            message = null;
        }
        
        return message;
    }
}
