package com.example.jpaexamples.example08.optimistic.repository;

import com.example.jpaexamples.example08.optimistic.entity.User08;
import com.example.jpaexamples.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User08Repository extends BaseRepository<User08, Integer> {
}
