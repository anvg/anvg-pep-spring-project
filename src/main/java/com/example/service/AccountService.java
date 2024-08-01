package com.example.service;

import com.example.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;

import java.util.*;

@Service
public class AccountService {

    AccountRepository accountRepository;
    
    @Autowired
    AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerUser(Account account){
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        
        if(optionalAccount.isPresent()){
            account = null;
        }else{
            account = accountRepository.save(account);
        }
        
        return account;
    }

    public Account loginUser(Account account){
        Optional<Account> optionalLogin = accountRepository.findByUsernameAndPassword(
            account.getUsername(), account.getPassword());
        Account target = null;

        if(optionalLogin.isPresent()){
            target = account;
            target.setAccountId(optionalLogin.get().getAccountId());
            target = accountRepository.save(target);
        }

        return target;
    }
}
