package com.example.springmvcexamples.example05;

import com.example.springmvcexamples.example05.textencryptor.MyToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

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
        MyToken token = new MyToken();
        token.setUid(1);
        token.setRole(MyToken.Role.ADMIN);
        TextEncryptor encryptor = Encryptors.text(secretKey, salt);
        try {
            String json = objectMapper.writeValueAsString(token);
            String r = encryptor.encrypt(json);
            log.debug(r);
            log.debug("{}", r.length());
            log.debug(encryptor.encrypt(json));

            String reJson = encryptor.decrypt(r);
            MyToken reToken = objectMapper.readValue(reJson, MyToken.class);
            log.debug(reToken.getRole().toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
