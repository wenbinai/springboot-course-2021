package com.example.jpaexamples.example02;

import com.example.jpaexamples.example02.onetomany.Address02;
import com.example.jpaexamples.example02.onetomany.User02;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class OneToManyTest {
    @Autowired
    private EntityManager manager;

    @Test
    public void test_addUserAddress() {
        User02 u = new User02();
        u.setName("BO");
        manager.persist(u);

        Address02 a1 = new Address02();
        a1.setDetail("956");
        manager.persist(a1);

        Address02 a2 = new Address02();
        a2.setDetail("956");
        manager.persist(a2);
    }

    @Test
    public void test_rel() {
        User02 u = manager.find(User02.class, 1);

        Address02 a1 = manager.find(Address02.class, 1);
        a1.setUser(u);
        Address02 a2 = manager.find(Address02.class, 2);
        a2.setUser(u);
    }

    @Test
    public void test_rel2() {
        User02 u = manager.find(User02.class, 1);
        Address02 a1 = manager.find(Address02.class, 1);
        Address02 a2 = manager.find(Address02.class, 2);

        u.setAddresses(List.of(a1, a2));

    }

    @Test
    public void test_rel02() {

    }
}
