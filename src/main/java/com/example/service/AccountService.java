package com.example.service;

import com.example.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;

import java.util.*;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    
    // @Autowired
    // AccountService(AccountRepository accountRepository){
    //     this.accountRepository = accountRepository;
    // }

    public ResponseEntity<Account> registerUser(Account account){
        Account addUser = new Account();
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        ResponseEntity<Account> responseBody = null;
        
        if(!optionalAccount.isPresent()){
            accountRepository.save(account);
            responseBody = ResponseEntity.status(200).body(account);
        }else{
            responseBody = ResponseEntity.status(409).body(null);
        }
        
        return responseBody;
    }

    public boolean loginUser(Account account){
        boolean loginSuccess = false;
        Optional<Account> optionalUsername = accountRepository.findByUsername(account.getUsername());
        Optional<Account> optionalPassword = accountRepository.findByPassword(account.getPassword());

        if(optionalUsername.isPresent() && optionalPassword.isPresent()){
            loginSuccess = true;
        }

        return loginSuccess;
    }
}
