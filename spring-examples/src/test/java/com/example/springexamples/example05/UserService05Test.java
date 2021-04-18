package com.example.springexamples.example05;

import com.example.springexamples.example05.transactions.service.UserService05;
import com.example.springexamples.example05.transactions.entity.User05;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserService05Test {
    @Autowired
    private UserService05 userService05;
    public void test() {
        User05 u = User05.builder().id(79L).name("SUN").build();
        userService05.addUser(u);
    }
}
