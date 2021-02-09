package com.example.mybatisexamples.example01;

import com.example.mybatisexamples.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper01 {
    @Insert("insert into user(id, name, company) values(#{id}, #{name}, #{company})")
    void insert(User user);

    @Select("select * from user")
    List<User> list();

    @Select("select * from user u where u.id=#{uid}")
    User get(@Param("uid") long uid);

}