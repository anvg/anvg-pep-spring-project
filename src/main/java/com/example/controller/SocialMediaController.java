package com.example.controller;

import com.example.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.repository.AccountRepository;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @Controller
public class SocialMediaController {

    AccountService accountService;

    @Autowired
    SocialMediaController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> registerUserHandler(@RequestBody Account account){


        Account target = accountService.registerUser(account);

        if(target != null){
            return new ResponseEntity<Account>(HttpStatus.OK);
        }

        return new ResponseEntity<Account>(HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public @ResponseBody void loginUserHandler(@RequestBody Account account){
        // boolean isValidCredential = accountService.loginUser(account);

        // if(isValidCredential){
        //     return ResponseEntity.status(200).body(null);
        // }
            
        // return ResponseEntity.status(401).body(null);
    }
}
