package com.example.repository;

import com.example.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    @Query("INSERT INTO Account(username) VALUES(?1)")
    public List<String> registerUser(String name);
}
