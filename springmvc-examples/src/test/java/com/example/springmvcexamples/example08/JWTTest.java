package com.example.springmvcexamples.example08;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springmvcexamples.example08.jwt.JWTComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Slf4j
public class JWTTest {
    @Autowired
    private JWTComponent jwtComponent;

    @Test
    public void test() throws InterruptedException {
        String token = jwtComponent.encode(Map.of("uid", 1384896304762638307L, "role", 9));
        log.debug(token);
        log.debug("{}", token.length());
        long uid = jwtComponent.decode(token).getClaim("uid").asLong();
        log.debug("{}", uid);

        Thread.sleep(15000);
        long uid2 = jwtComponent.decode(token).getClaim("uid").asLong();
        log.debug("{}", uid2);
    }

}
