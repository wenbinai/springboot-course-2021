package com.example.jpaexamples.example06.fetch.service;

import com.example.jpaexamples.example06.fetch.entity.Address06;
import com.example.jpaexamples.example06.fetch.entity.User06;
import com.example.jpaexamples.example06.fetch.repository.Address06Repository;
import com.example.jpaexamples.example06.fetch.repository.User06Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class User06Service {
    @Autowired
    private User06Repository user06Repository;
    @Autowired
    private Address06Repository address06Repository;

    public void addUser(User06 user06) {
        user06Repository.save(user06);
    }
    public void addAddress(Address06 address06) {
        address06Repository.save(address06);
    }

    public User06 getUser(int id) {
        User06 u = user06Repository.findById(id).orElse(null);
        return u;
    }

    public Address06 getAddress(int id) {
        return address06Repository.findById(id).orElse(null);
    }
}
