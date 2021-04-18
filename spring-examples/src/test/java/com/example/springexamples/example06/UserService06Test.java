package com.example.springexamples.example06;

import com.example.springexamples.example06.redis.User;
import com.example.springexamples.example06.redis.UserService06;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserService06Test {
    @Autowired
    private UserService06 userService06;

    @Test
    public void getUser_test() {
        User u = userService06.getUser(12);
        log.debug("{}", u);
    }

    @Test
    public void listUser_test() {
        List<User> users = userService06.listUsers();
        for (User u : users) {
            log.debug(u.toString());
        }
    }
}
