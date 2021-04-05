package com.example.jpaexamples.example07.jpql.repository;

import com.example.jpaexamples.example07.jpql.entity.User07;
import com.example.jpaexamples.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User07Repository extends BaseRepository<User07, Integer> {
    @Query("from User07 u where u.name=:name")
    List<User07> list(@Param("name") String name);

    List<User07> findByName(String name);

    @Modifying
    @Query("update User07 u set u.name=:newname where u.id=:id")
    int update(@Param("id") int id, @Param("newname") String name);

}
