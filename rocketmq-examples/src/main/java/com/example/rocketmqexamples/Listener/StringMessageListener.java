package com.example.rocketmqexamples.Listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = "mytopic", consumerGroup = "group-mytopic")
public class StringMessageListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.debug(s);
    }
}
