package com.example.redisexamples.service;

import com.example.redisexamples.entity.Item;
import com.example.redisexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, Integer> ho;
    // redis中key
    private String key = "items";

    // 初始化一个HashOperations操作对象，避免反复创建
    @PostConstruct
    public void init() {
        ho = redisTemplate.opsForHash();
    }

    // 在创建抢购活动时，从数据库提取商品ID/抢购数量等信息，提前置于redis
    public void addActivity(Item item) {
        ho.put(key, item.getId(), item.getTotal());

    }
    /**
     * 模拟商品数量固定的并发秒杀。入口应使用限流操作，确保并发下服务器不会崩溃
     * 此处将所有抢购商品置于一个redis hash，用单独的string也可但不利于管理
     *
     */
    public boolean addOrder(User user, Item item) {
        if (!ho.hasKey(key, item.getId())) {
            log.debug("商品不存在");
            return false;
        }
        // get()是多线程的，increment()/set()是单线程
        // 在并发下通过get()值判断商品当前剩余数量，是错误的
        // 此处在商品抢光后继续减为负数，从而计算总抢购人数。可按需修改
        if (ho.increment(key, item.getId(), -1) < 0) {
            // log.debug("商品已被抢光");
            return false;
        }
        // 抢购成功，执行创建订单等操作
        // 商品已经抢到，为减轻服务器/数据库压力，可以将订单处理操作发送至消息队列异步处理
        /*try {
            Thread.sleep(20);
        } catch (InterruptedException ignored) {
        }*/
        return true;
    }
}
