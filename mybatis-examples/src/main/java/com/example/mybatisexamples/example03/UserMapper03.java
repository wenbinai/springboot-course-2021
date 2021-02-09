package com.example.mybatisexamples.example03;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisexamples.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper03 extends BaseMapper<User> {
    @Select("select u.* from user u join address a " +
            "on u.id = a.user_id " +
            "where a.detail=#{detail}")
    List<User> listByDetail(String detail);
}