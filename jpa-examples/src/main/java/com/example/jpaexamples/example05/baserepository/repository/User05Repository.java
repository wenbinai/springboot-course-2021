package com.example.jpaexamples.example05.baserepository.repository;

import com.example.jpaexamples.example05.baserepository.entity.User05;
import com.example.jpaexamples.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User05Repository extends BaseRepository<User05, Integer> {
}
