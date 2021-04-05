package com.example.jpaexamples.example11.noconstraint.repoistory;

import com.example.jpaexamples.example11.noconstraint.entity.Address11;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Address11Repository extends JpaRepository<Address11, Integer> {
    @Query("from Address11 a where a.user.id=:uid")
    List<Address11> list(@Param("uid") int uid);
}
