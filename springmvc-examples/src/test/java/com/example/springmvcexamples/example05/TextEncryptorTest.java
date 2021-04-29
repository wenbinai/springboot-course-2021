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
        Map<String, Object> map = Map.of("uid", 1384896304762638307L, "role", 9);
        String r = encrypt.encrypt(map);
        log.debug(r);
        log.debug("{}", r.length());
        log.debug(encrypt.encrypt(map));
    }

    @Test
    public void test_decrypt() {
        String auth = "b3a60e67dfcd220874e36569f623829ea97d556d646b4eb208c2f43" +
                "b452bbf61a3e5982e0a52810517bcc734a5561e2dc53a9e3854d5fd4afebf0b15b7c1ad5c";
        Map<String, Object> token = encrypt.decrypt(auth);
        log.debug("{}", token.get("uid"));
        log.debug("{}", token.get("role"));
    }
}
