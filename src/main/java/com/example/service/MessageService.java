package com.example.service;

import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;

import java.util.*;

@Service
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message){
        
        Optional<Message> optionalMessage = 
        messageRepository.findByPostedBy(message.getPostedBy());
        final int TOTAL_RECORDS = (int) messageRepository.count();
        final boolean MESSAGE_HAS_CONTENT = message.getMessageText().length() > 0;
        final boolean MESSAGE_BELOW_CHARACTER_LIMIT = 
        message.getMessageText().length() <= 255;


        if(optionalMessage.isPresent() && 
        MESSAGE_HAS_CONTENT && MESSAGE_BELOW_CHARACTER_LIMIT){
            message.setMessageId(TOTAL_RECORDS + 1);
            message = messageRepository.save(message);
        }else{
            message = null;
        }
        
        return message;
    }

    public List<Message> retrieveAllMessages(){
        List<Message> messageList = messageRepository.findAll();

        return messageList;
    }

    public Message retrieveAllMessagesById(int id){
        Optional<Message> messageListById = messageRepository.findByMessageId(id);
        Message target = null;

        if(messageListById.isPresent()){
            target = messageListById.get();
        }

        return target;
    }

    public Integer deleteMessageById(int id){
        
        final Integer ONE_ROW_DELETED = 1;
        Integer deletedMessage = null;
        boolean messageExists = messageRepository.existsById(id);
        

        if(messageExists){
            deleteMessageById(id);
            deletedMessage = ONE_ROW_DELETED;
        }

        return deletedMessage;
    }
}
