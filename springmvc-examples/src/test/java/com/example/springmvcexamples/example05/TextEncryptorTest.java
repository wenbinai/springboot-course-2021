package com.example.springmvcexamples.example05;

import com.example.springmvcexamples.example05.textencryptor.EncryptComponent05;
import com.example.springmvcexamples.example05.textencryptor.MyToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TextEncryptorTest {
    @Autowired
    private EncryptComponent05 encrypt;
    @Test
    public void test_encrypt() {
        MyToken token = new MyToken();
        token.setUid(1);
        token.setRole(MyToken.Role.ADMIN);
        String r = encrypt.encryptToken(token);
        log.debug(r);
        log.debug("{}", r.length());
        log.debug(encrypt.encryptToken(token));
    }

    @Test
    public void test_decrypt() {
        String auth = "744193c872b677aab12a0ced447882f4cf9fc" +
                "a92a09d428a26ed145ed2ed2eec665c8824ebc353042ba2be136efcb5c6";
        MyToken token = encrypt.decryptToken(auth);

        log.debug("{}", token.getUid());
        log.debug("{}", token.getRole());
    }
}
