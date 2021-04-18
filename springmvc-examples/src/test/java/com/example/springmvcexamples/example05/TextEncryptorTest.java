package com.example.springmvcexamples.example05;

import com.example.springmvcexamples.example05.textencryptor.EncryptComponent05;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Slf4j
public class TextEncryptorTest {
    @Autowired
    private EncryptComponent05 encrypt;
    @Test
    public void test_encrypt() {
        Map<String, Object> map = Map.of("uid", 24, "role", "admin");
        String r = encrypt.encrypt(map);
        log.debug(r);
        log.debug("{}", r.length());
        log.debug(encrypt.encrypt(map));
    }

    @Test
    public void test_decrypt() {
        String auth = "fc2624419fae90bc4c7c7a4759ca5e8446e5fa99" +
                "5e8c1de47fc7c9488db14b8c90f5ac3804136c809e8ad712ae8fe8ae";
        Map<String, Object> token = encrypt.decrypt(auth);
        log.debug("{}", token.get("uid"));
        log.debug("{}", token.get("role"));
    }
}
