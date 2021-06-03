package com.example.rocketmqexamples.controller;

import com.example.rocketmqexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/")
@Slf4j
public class MyController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("topic")
    public void getIndex() {
        rocketMQTemplate.convertAndSend("mytopic", LocalDateTime.now());
    }

    @GetMapping("topic2")
    public void getIndex2() {
        rocketMQTemplate.convertAndSend("user", User.builder().id(1L).name(LocalDateTime.now().toString()).build());
    }

    @GetMapping("tx/{purpose}")
    public void getTx(@PathVariable String purpose) {
        Message<User> m = MessageBuilder.withPayload(User.builder().id(1L).name(LocalDateTime.now().toString()).build())
                .build();
        rocketMQTemplate.sendMessageInTransaction("tx", m, purpose);

    }
}

