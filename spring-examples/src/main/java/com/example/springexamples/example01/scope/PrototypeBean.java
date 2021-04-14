package com.example.springexamples.example01.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope("prototype")
public class PrototypeBean {
    @Autowired
    private SingletonBean singletonBean;
    public void getSingle() {
        log.debug("singleton in prototype: {}", singletonBean);
    }
}
