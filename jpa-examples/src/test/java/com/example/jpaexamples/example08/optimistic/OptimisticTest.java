package com.example.jpaexamples.example08.optimistic;

import com.example.jpaexamples.example08.optimistic.entity.User08;
import com.example.jpaexamples.example08.optimistic.service.User08Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OptimisticTest {
    @Autowired
    private User08Service user08Service;

    @Test
    public void init() {
        User08 u = new User08();
        u.setName("BO");
        user08Service.addUser(u);
    }

    @Test
    public void test_updateUser() throws InterruptedException {
        new Thread(() -> {
            user08Service.updateUser(1, "aaa");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            user08Service.updateUser(1, "bbb");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(2000);
    }
}
