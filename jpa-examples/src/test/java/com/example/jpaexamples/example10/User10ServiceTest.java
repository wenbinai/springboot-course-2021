package com.example.jpaexamples.example10;

import com.example.jpaexamples.example10.rollback.entity.User10;
import com.example.jpaexamples.example10.rollback.service.User10Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class User10ServiceTest {
    @Autowired
    private User10Service user10Service;

    @Test
    public void test_addUser() {
        User10 u = new User10();
        u.setName("5");
        user10Service.addUser(u);
    }
}
