package com.example.jpaexamples.example09.pessimistic;

import com.example.jpaexamples.example09.pessimistic.entity.User09;
import com.example.jpaexamples.example09.pessimistic.service.User09Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PessimisticTest {
    @Autowired
    private User09Service user09Service;
    @Test
    public void init() {
        User09 user09 = new User09();
        user09.setName("BO");
        user09.setBalance(1000);
        user09Service.addUser(user09);
    }

    @Test
    public void test_buy() throws InterruptedException {
        new Thread(() -> {
            user09Service.buy(1, 800);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            user09Service.buy(1, 800);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(2000);
    }
}
