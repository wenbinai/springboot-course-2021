package com.example.mybatisexamples.example02;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisexamples.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper02 extends BaseMapper<User> {
    @Select("select id, name from user where company=#{comp}")
    List<User> listByCompany(String comp);
}