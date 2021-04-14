package com.example.springexamples.example05.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService05 {
    // idea不支持自定义注解中SpEL的自动提示
    @RedisCache(value = "user", key = "#uid")
    public User getUser(long uid) {
        log.debug("getUser()");
        return User.builder().id(12L).name("SUN").build();
    }

    @RedisCache(value = "users", ttl = 5)
    public List<User> listUsers() {
        log.debug("listUsers()");
        User u = User.builder().id(12L).name("SUN").build();
        User u2 = User.builder().id(15L).name("BO").build();
        return List.of(u, u2);
    }

    @RedisPut(value = "user", key = "#user.id")
    public User addUser(User user) {
        log.debug("addUser()");
        return user;
    }

    @RedisEvict(value = "user", key = "#uid")
    public void removeUser(long uid) {
        log.debug("removeUser()");
    }
}
