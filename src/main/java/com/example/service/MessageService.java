package com.example.service;

import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;

@Service
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message){
        
        final boolean MESSAGE_HAS_CONTENT = message.getMessageText().length() > 0;
        final boolean MESSAGE_BELOW_CHARACTER_LIMIT = 
        message.getMessageText().length() <= 255;

        if(MESSAGE_HAS_CONTENT && MESSAGE_BELOW_CHARACTER_LIMIT){
            message = messageRepository.save(message);
        }else{
            message = null;
        }
        
        return message;
    }
}
