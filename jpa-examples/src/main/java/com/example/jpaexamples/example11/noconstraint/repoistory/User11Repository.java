package com.example.jpaexamples.example11.noconstraint.repoistory;

import com.example.jpaexamples.example11.noconstraint.entity.User11;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User11Repository extends JpaRepository<User11, Integer> {
}
