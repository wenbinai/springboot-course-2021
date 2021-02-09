package com.example.mybatisexamples.example02;

import com.example.mybatisexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
@Rollback(value = false)
@Slf4j
public class UserMapper02Test {
    @Autowired
    private UserMapper02 userMapper02;

    @Test
    public void addUser_test() {
        User user = new User();
        user.setName("SUN");
        user.setCompany("amazon");
        userMapper02.insert(user);
    }
    // 模拟前端传入修改company数据
    @Test
    public void update_test() {
        User u = User.builder()
                .id(1357765527753269249L)
                .company("nike")
                .build();
        userMapper02.updateById(u);
    }

    @Test
    public void list_test() {
        List<User> users = userMapper02.selectByMap(Map.of("company", "nike"));
        for (User user : users) {
            log.debug(user.getName());
        }
    }
    @Test
    public void list_test2() {
        List<User> users = userMapper02.listByCompany("nike");
        for (User user : users) {
            log.debug(user.getName());
        }
    }
}
