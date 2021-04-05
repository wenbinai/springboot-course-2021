package com.example.jpaexamples.example05.baserepository;

import com.example.jpaexamples.example05.baserepository.entity.User05;
import com.example.jpaexamples.example05.baserepository.repository.User05Repository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class User05RepositoryTest {
    @Autowired
    private User05Repository user05Repository;
    @Autowired
    EntityManager manager;
    @Test
    public void test_addUser() {
        User05 user05 = new User05();
        user05.setName("a");
        user05Repository.save(user05);
        user05.setName("b");
    }
    @Test
    public void test_addUser2() {
        User05 user05 = user05Repository.findById(4).orElse(null);
        log.debug("{}", user05.getInsertTime());
    }
    @Test
    public void test_addUser3() {
        User05 user05 = new User05();
        user05.setId(2);
        user05Repository.save(user05);
    }
    @Test
    public void test_addUser4() {
        User05 user05 = new User05();
        user05.setName("BO");
        User05 u = user05Repository.save(user05);
        log.debug("{}", u.getInsertTime());
    }

    @Test
    public void test_refresh() {
        User05 user05 = new User05();
        user05.setName("SUN");
        manager.persist(user05);
        user05.setName("BO");
        manager.refresh(user05);
        log.debug("{}", user05.getName());
        log.debug("{}", user05.getId());
        log.debug("{}", user05.getInsertTime());
    }
    @Test
    public void test_refresh2() {
        User05 user05 = new User05();
        user05.setName("SUN");
        user05Repository.save(user05);
        user05Repository.refresh(user05);
        log.debug("{}", user05.getName());
        log.debug("{}", user05.getId());
        log.debug("{}", user05.getInsertTime());
    }

}
