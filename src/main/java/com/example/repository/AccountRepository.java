package com.example.repository;

import com.example.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Optional<Account> findByUsername(String username);    
    Optional<Account> findByUsernameAndPassword(String username, String password);

}
