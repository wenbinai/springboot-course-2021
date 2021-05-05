package com.example.springexamples.example06.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService06 {
    // idea不支持自定义注解中SpEL的自动提示
    @RedisCacheable(value = "user", key = "#uid")
    public User getUser(long uid) {
        log.debug("getUser()");
        return User.builder().id(12L).name("SUN").build();
    }

    @RedisCacheable(value = "users", ttl = 60, timeUnit = TimeUnit.SECONDS)
    public List<User> listUsers() {
        log.debug("listUsers()");
        User u = User.builder().id(12L).name("SUN").build();
        User u2 = User.builder().id(15L).name("BO").build();
        return List.of(u, u2);
    }

    @RedisCachePut(value = "user", key = "#user.id")
    public User addUser(User user) {
        log.debug("addUser()");
        return user;
    }

    @RedisCacheEvict(value = "user", key = "#uid")
    public void removeUser(long uid) {
        log.debug("removeUser()");
    }
}
