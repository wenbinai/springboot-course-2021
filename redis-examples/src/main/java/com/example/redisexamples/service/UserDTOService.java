package com.example.redisexamples.service;

import com.example.redisexamples.dto.UserDTO;
import com.example.redisexamples.mapper.UserDTOMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserDTOService {
    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    @Qualifier("cum")
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    private String preKey = "userdto:";
    public UserDTO getUserDTO(long uid) {
        String re = (String) redisTemplate.opsForValue().get(preKey + uid);
        UserDTO userDTO = null;
        if (re == null) {
            userDTO = userDTOMapper.get(uid);
            try {
                redisTemplate.opsForValue().set(preKey + uid, objectMapper.writeValueAsString(userDTO));
                redisTemplate.expire(preKey + uid, 2, TimeUnit.MINUTES);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                userDTO = objectMapper.readValue(re, UserDTO.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return userDTO;
    }

    //@Transactional
    int c = 0;
    public int updateUserDTO(long uid) {
        String result = (String) redisTemplate.opsForValue().get(preKey + uid);

        UserDTO userDTO = null;
        try {
            userDTO = objectMapper.readValue(result, UserDTO.class);
            int count = userDTO.getCount();
            if (count == 0) {
                return -1;
            }
            c++;
            log.debug("{}", c);
            userDTO.setCount(count - 1);
            redisTemplate.opsForValue().set(preKey + uid, objectMapper.writeValueAsString(userDTO));
            redisTemplate.expire(preKey + uid, 2, TimeUnit.MINUTES);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
