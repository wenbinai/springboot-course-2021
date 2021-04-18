package com.example.springmvcexamples.example05;

import com.example.springmvcexamples.example04.passwordencoder.entity.User04;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Slf4j
public class ObjectMapperTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void test_mapper() {
        User04 u = new User04();
        u.setUserName("BO");
        u.setPassword("123456");
        try {
            String json = objectMapper.writeValueAsString(u);
            log.debug(json);
            User04 u2 = objectMapper.readValue(json, User04.class);
            log.debug(u2.getUserName());
            log.debug(u2.getPassword());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_mapper2() throws JsonProcessingException {
        User04 u = new User04();
        u.setUserName("BO");
        u.setPassword("123456");
        Map<String, User04> map = Map.of("user", u);
        String json = objectMapper.writeValueAsString(map);
        log.debug(json);

        Map<String, User04> reMap = objectMapper.readValue(json, new TypeReference<Map<String, User04>>() {});

        reMap.forEach((k, v) -> {
            log.debug(k);
            log.debug(v.getUserName() + "/" + v.getPassword());
        });
    }

}
