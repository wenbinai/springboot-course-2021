package com.example.mybatisexamples.example01;

import com.example.mybatisexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
@Slf4j
public class UserMapper01Test {
    @Autowired
    private UserMapper01 userMapper01;

    @Test
    public void addUser_test() {
        User user = new User();
        user.setId(3L);
        user.setName("BO");
        user.setCompany("facebook");
        userMapper01.insert(user);
    }

    @Test
    public void list_test() {
        for (User user : userMapper01.list()) {
            log.debug("{}/{}/{}",
                    user.getName(),
                    user.getCompany(),
                    user.getCreateTime());
        }
    }

    @Test
    public void get_test() {
        User user = userMapper01.get(2L);
        log.debug("{}/{}",user.getName(),user.getUpdateTime());
    }
}
