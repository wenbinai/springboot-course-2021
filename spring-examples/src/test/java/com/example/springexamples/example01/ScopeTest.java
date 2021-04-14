package com.example.springexamples.example01;

import com.example.springexamples.example01.scope.PrototypeBean;
import com.example.springexamples.example01.scope.SingletonBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ScopeTest {
    @Autowired
    private PrototypeBean prototypeBean;
    @Autowired
    private SingletonBean singletonBean;
    @Test
    public void test() {
        log.debug("prototype in test: {}", prototypeBean);
        log.debug("singleton in test: {}", singletonBean);
        prototypeBean.getSingle();
        singletonBean.getProto();
    }

}