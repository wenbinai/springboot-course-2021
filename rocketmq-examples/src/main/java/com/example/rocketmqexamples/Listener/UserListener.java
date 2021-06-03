package com.example.rocketmqexamples.Listener;

import com.example.rocketmqexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "user", consumerGroup = "user-group")
@Slf4j
public class UserListener implements RocketMQListener<User> {
    @Override
    public void onMessage(User user) {
        log.debug(user.getName());
    }
}
