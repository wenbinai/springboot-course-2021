package com.example.rocketmqexamples.Listener;
import com.example.rocketmqexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = "tx", consumerGroup = "group-tx")
public class TransactionMessageListener implements RocketMQListener<User> {
    @Override
    public void onMessage(User s) {
        // 当事务消息状态为commit时，消息服务器才会真正发出消息
        // 此处监听才会回调消费
        log.debug(s.getName());
    }
}
