package com.example.springexamples.example05;

import com.example.springexamples.example05.redis.User;
import com.example.springexamples.example05.redis.UserService05;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserService05Test {
    @Autowired
    private UserService05 userService05;

    @Test
    public void getUser_test() {
        User u = userService05.getUser(12);
        log.debug("{}", u);
    }

    @Test
    public void listUser_test() {
        List<User> users = userService05.listUsers();
        for (User u : users) {
            log.debug(u.toString());
        }
    }
}
