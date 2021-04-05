package com.example.jpaexamples.example09.pessimistic.service;

import com.example.jpaexamples.example09.pessimistic.entity.User09;
import com.example.jpaexamples.example09.pessimistic.repository.User09Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class User09Service {
    @Autowired
    private User09Repository user09Repository;

    public void addUser(User09 u) {
        user09Repository.save(u);
    }

    public void buy(int uid, int expense) {
        User09 u = user09Repository.find(1);
        int newBanance = u.getBalance() - expense;
        if (newBanance >= 0) {
            u.setBalance(newBanance);
            log.debug("花费后余额：{}", newBanance);
        } else {
            log.debug("余额不足");
        }

    }
}
