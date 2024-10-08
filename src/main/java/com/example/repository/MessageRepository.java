package com.example.repository;

import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MessageRepository extends JpaRepository<Message, Integer>{

    Optional<Message> findByPostedBy(int id);
    Optional<Message> findByMessageId(int id);

    Optional<List<Message>> findAllByPostedBy(int postedBy);
}
