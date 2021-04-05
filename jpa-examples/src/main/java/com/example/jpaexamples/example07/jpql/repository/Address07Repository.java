package com.example.jpaexamples.example07.jpql.repository;

import com.example.jpaexamples.example07.jpql.entity.Address07;
import com.example.jpaexamples.example07.jpql.entity.User07;
import com.example.jpaexamples.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Address07Repository extends BaseRepository<Address07, Integer> {
    @Query("select a.user from Address07 a where a.detail=:detail")
    List<User07> list(@Param("detail") String detail);

    @Query("select a.user from Address07 a where a.user.id=:uid")
    User07 find(@Param("uid") int uid);

    @Query("select a.user from Address07 a where a.detail=:detail and a.user.name=:uname")
    List<User07> list(@Param("detail") String detail, @Param("uname") String uname);

    @Query("from Address07 a where a.detail=:detail")
    Page<Address07> list(@Param("detail") String detail, Pageable pageable);

}
