package com.example.springmvcexamples.example05;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class EncryptorTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${my.secretkey}")
    private String secretKey;
    @Value("${my.salt}")
    private String salt;
    @Test
    public void test_encrypt() {
        TextEncryptor encryptor = Encryptors.text(secretKey, salt);
        try {
            Map<String, Object> map = Map.of("uid", 1384896304762638307L, "role", 9);
            String json = objectMapper.writeValueAsString(map);
            String r = encryptor.encrypt(json);
            log.debug(r);
            log.debug("{}", r.length());
            log.debug(encryptor.encrypt(json));

            String reJson = encryptor.decrypt(r);
            Map<String, Object> reToken = objectMapper.readValue(reJson, Map.class);
            log.debug(reToken.get("role").toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
