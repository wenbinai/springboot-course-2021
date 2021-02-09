package com.example.mybatisexamples.example03;

import com.example.mybatisexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapper03Test {
    @Autowired
    private UserMapper03 userMapper03;
    @Test
    public void list_test() {
        for (User u : userMapper03.listByDetail("211")) {
            log.debug("{}/{}/{}",u.getId(), u.getName(), u.getCreateTime());
        }
    }
}
