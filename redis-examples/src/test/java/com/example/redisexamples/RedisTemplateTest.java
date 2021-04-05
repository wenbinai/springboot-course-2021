package com.example.redisexamples;

import com.example.redisexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        User user = User.builder().id(1L).name("BO").build();
        redisTemplate.opsForValue().set("1",user);
    }

    @Test
    public void test2() {
        //User user = redisTemplate.opsForValue().g
    }
}
