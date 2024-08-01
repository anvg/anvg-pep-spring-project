package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @Controller
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    @Autowired
    SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> registerUserHandler(@RequestBody Account account){

        Account target = accountService.registerUser(account);
        ResponseEntity<Account> responseEntity  = new ResponseEntity<Account>(account, HttpStatus.CONFLICT);

        if(target != null){
            responseEntity = new ResponseEntity<Account>(account, HttpStatus.OK);
        }

        return responseEntity;
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> loginUserHandler(@RequestBody Account account){
        Account target = accountService.loginUser(account);
        ResponseEntity<Account> responseEntity = 
        new ResponseEntity<>(target, HttpStatus.UNAUTHORIZED);

        if(target != null){
            responseEntity = new ResponseEntity<Account>(target, HttpStatus.OK);
        }
            
        return responseEntity;
    }

    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> createMessageHandler(@RequestBody Message message){
        Message target = messageService.createMessage(message);
        ResponseEntity<Message> reponseEntity = 
        new ResponseEntity<Message>(target, HttpStatus.BAD_REQUEST);

        if(target != null){
            reponseEntity = new ResponseEntity<Message>(target, HttpStatus.OK);
        }
        
        return reponseEntity;
    }

    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> retrieveAllMessageHandler(){
        List<Message> resultList = messageService.retrieveAllMessages();

        return new ResponseEntity<List<Message>>(resultList, HttpStatus.OK);
    }

    @GetMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Message> retrieveAllMessageIdHandler(@PathVariable int messageId){
        Message target = messageService.retrieveAllMessagesById(messageId);

        return new ResponseEntity<>(target, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        boolean isDeleted = messageService.deleteMessageById(messageId);
        final Integer ONE_MESSAGE_DELETED = 1;
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        
        if(isDeleted){
            responseEntity = new ResponseEntity<>(ONE_MESSAGE_DELETED, HttpStatus.OK);
        }

        return responseEntity;
        
    }

    @PatchMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> updatedMessageById(@RequestBody Message message, @PathVariable int messageId){
        Message target = messageService.updateMessageById(message, messageId);

        if(target != null){
            return new ResponseEntity<>(1, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody ResponseEntity<List<Message>> retrieveAllMessageByUserHandler(@PathVariable int accountId, Message message){
        List<Message> messageList = messageService.retrieveAllByMessageByUser(accountId, message);

        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }
    
    
}
