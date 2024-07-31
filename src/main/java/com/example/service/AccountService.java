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
        String searchUser = accountRepository.findUserByUsername(account.getUsername());
        
        if(searchUser != null){
            addUser.setUsername(account.getUsername());
            addUser.setPassword(account.getPassword());
            accountRepository.save(account);
        }
        
        return addUser;
    }
}
