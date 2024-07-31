package com.example.service;

import com.example.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;

import java.util.*;

@Service
public class AccountService {
    AccountRepository accountRepository;
    
    // @Autowired
    // AccountService(AccountRepository accountRepository){
    //     this.accountRepository = accountRepository;
    // }

    public Account registerUser(Account account){
        Account addUser = new Account();
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        
        if(optionalAccount.isPresent()){
            addUser.setUsername(account.getUsername());
            addUser.setPassword(account.getPassword());
            accountRepository.save(account);
        }
        
        return addUser;
    }

    // public boolean loginUser(Account account){
    //     boolean loginSuccess = false;
    //     String username = accountRepository.findByUsername(account.getUsername());
    //     String password = accountRepository.findByPassword(account.getPassword());

    //     // if(username != null && password != null){
    //     //     loginSuccess = true;
    //     // }

    //     return loginSuccess;
    }
}
