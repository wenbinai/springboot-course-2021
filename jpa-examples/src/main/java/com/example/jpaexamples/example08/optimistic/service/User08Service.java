package com.example.jpaexamples.example08.optimistic.service;

import com.example.jpaexamples.example08.optimistic.entity.User08;
import com.example.jpaexamples.example08.optimistic.repository.User08Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class User08Service {
    @Autowired
    private User08Repository user08Repository;

    public void addUser(User08 u) {
        user08Repository.save(u);
    }

    public void updateUser(int id, String nname) {
        user08Repository.findById(id)
                .orElse(null)
                .setName(nname);
    }
}
