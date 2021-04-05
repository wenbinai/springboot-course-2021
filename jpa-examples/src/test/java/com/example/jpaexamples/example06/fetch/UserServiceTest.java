package com.example.jpaexamples.example06.fetch;

import com.example.jpaexamples.example06.fetch.entity.Address06;
import com.example.jpaexamples.example06.fetch.entity.User06;
import com.example.jpaexamples.example06.fetch.service.User06Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private User06Service user06Service;

    @Test
    public void init() {
        User06 u = new User06();
        u.setName("BO");
        user06Service.addUser(u);

        Address06 a = new Address06();
        a.setDetail("956");
        a.setUser(u);
        user06Service.addAddress(a);

        Address06 a2 = new Address06();
        a2.setDetail("925");
        a2.setUser(u);
        user06Service.addAddress(a2);
    }

    @Test
    public void test_fetch() {
        log.debug(user06Service.getAddress(1).getUser().getName());
    }

    @Test
    public void test_fetch2() {
        user06Service.getUser(1)
                .getAddresses()
                .forEach(a -> log.debug(a.getDetail()));
    }
}
