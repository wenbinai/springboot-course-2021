package com.example.rocketmqexamples.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQTransactionListener
public class RocketMQTransactionHandler implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.debug("executeLocalTransaction");
        // 自动创建消息头唯一uuid值
        // 可存储在此服务独立的事务表，用于checkLocalTransaction()方法回调查询状态
        log.debug("{}", message.getHeaders().get(MessageHeaders.ID));
        // 消息体，是字节数组
        log.debug("message.getPayload(), {}", message.getPayload());
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 业务逻辑操作，决定事务消息状态
        RocketMQLocalTransactionState s = RocketMQLocalTransactionState.COMMIT;
        if (o.equals("rollback")) {
            s = RocketMQLocalTransactionState.ROLLBACK;
        }
        if (o.toString().equals("unknown")) {
            s = RocketMQLocalTransactionState.UNKNOWN;
        }

        return s;
    }

    // 如果消息队列有UNKNOWN状态消息，会反复回调此方法，确定消息是提交发送还是回滚取消
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.debug("checkLocalTransaction");
        log.debug((String) message.getPayload());
        return RocketMQLocalTransactionState.UNKNOWN;
    }
}
