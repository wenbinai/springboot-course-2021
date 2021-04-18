package com.example.springexamples.example05.transactions.service;

import com.example.springexamples.example05.transactions.entity.User05;
import com.example.springexamples.example05.transactions.mapper.UserMapper05;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
public class UserService05 {
    @Autowired
    private UserMapper05 userMapper05;
    public void addUser(User05 user) {
        userMapper05.insert(user);
        try {
            Files.readString(Path.of("A:/aa.aa"));
        } catch (IOException e) {
            throw new RuntimeException("操作错误");
        }
    }
}
