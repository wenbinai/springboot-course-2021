package com.example.jpaexamples.example09.pessimistic.repository;

import com.example.jpaexamples.example09.pessimistic.entity.User09;
import com.example.jpaexamples.repository.BaseRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface User09Repository extends BaseRepository<User09, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("from User09 u where u.id=:id")
    User09 find(@Param("id") int id);
}
