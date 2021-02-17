package com.example.cacheexamples.service;

import com.example.cacheexamples.entity.User;
import com.example.cacheexamples.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Cacheable(value = "user", key = "#uid")
    public User getUser(long uid) {
        User user = userMapper.getUser(uid);
        log.debug("called UserService getUser(), name: {}", user.getName());
        return user;
    }
    @CachePut(value = "user", key = "#user.id")
    public User updateUser(User user) {
        User u = userMapper.updateUser(user);
        log.debug("updateUser(), name: {}", u.getName());
        return user;
    }

    @CacheEvict(value = "user", key = "#uid")
    public void delUser(long uid) {
        // 从缓存删除，没有调用模拟的持久层删除
        // 因此会实际调用getUser()方法，重新从持久层获取
    }
}
