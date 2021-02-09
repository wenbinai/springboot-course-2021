package com.example.mybatisexamples.example07;

import com.example.mybatisexamples.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AccountMapperTest {
    @Autowired
    private AccountService07 accountService07;
    @Test
    public void version_test2() throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                Account a = accountService07.buy(1, 600);
                log.debug(a.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                Account a = accountService07.buy(1, 600);
                log.debug(a.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 确保测试线程在工作线程结束后结束
        Thread.sleep(2000);
    }
}
