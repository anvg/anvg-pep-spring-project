package com.example.service;

import com.example.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;

import java.util.*;

@Service
public class AccountService {

    // @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerUser(Account account){
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername()); //find if username exist
        //isPresent is true if username already exist
        //isPresent is false if username doesn't exist, in that case save that information
        
        if(!optionalAccount.isPresent()){
            accountRepository.save(account);
        }
        
        return account;
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
