package com.example.jpaexamples.example11;

import com.example.jpaexamples.example11.noconstraint.entity.Address11;
import com.example.jpaexamples.example11.noconstraint.repoistory.Address11Repository;
import com.example.jpaexamples.example11.noconstraint.entity.User11;
import com.example.jpaexamples.example11.noconstraint.repoistory.User11Repository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class NoForginTest {
    @Autowired
    private User11Repository user11Repository;
    @Autowired
    private Address11Repository address11Repository;

    @Test
    @Transactional
    public void test1() {
        User11 user11 = user11Repository.getOne(1);
        log.debug("{}", user11.getAddresses().size());
    }
    @Test
    @Transactional
    public void test2() {
        for (Address11 a : address11Repository.list(1)) {
            log.debug(a.getDetail());
        }
    }
}
