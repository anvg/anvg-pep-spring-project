package com.example.service;

import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.entity.Account;

import java.util.*;

@Service
public class MessageService {

    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message){
        
        Optional<Message> optionalMessage = 
        messageRepository.findByPostedBy(message.getPostedBy());
        final boolean MESSAGE_HAS_CONTENT = message.getMessageText().length() > 0;
        final boolean MESSAGE_BELOW_CHARACTER_LIMIT = 
        message.getMessageText().length() <= 255;


        if(optionalMessage.isPresent() && 
        MESSAGE_HAS_CONTENT && MESSAGE_BELOW_CHARACTER_LIMIT){
            // message = messageRepository.save(message);
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

    public boolean deleteMessageById(int id){
        
        boolean messageIsDeleted = false;
        Optional<Message> messageExists = messageRepository.findById(id);
        Message target = null;
        

        if(messageExists.isPresent()){
            messageRepository.deleteById(id);
            messageIsDeleted = true;
        }

        return messageIsDeleted;
    }

    public Message updateMessageById(Message body, int messageId){
        Optional<Message> existingMessage = messageRepository.findById(messageId);
        final boolean MESSAGE_HAS_CONTENT = body.getMessageText().length() > 0;
        final boolean MESSAGE_UNDER_255_CHARACTER_LIMIT = 
        body.getMessageText().length() <= 255;

        if(MESSAGE_HAS_CONTENT && MESSAGE_UNDER_255_CHARACTER_LIMIT){
            if(existingMessage.isPresent()){
                existingMessage.get().setMessageText(body.getMessageText());
                return messageRepository.save(existingMessage.get());
            }
        }

        return null;
    }

    public List<Message> retrieveAllByMessageByUser(int accountId, Message message){
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Optional<List<Message>> messageList = null;

        if(optionalAccount.isPresent()){
            messageList = messageRepository.findAllByPostedBy(accountId);
        }
        
        return messageList.get();

    }
}
