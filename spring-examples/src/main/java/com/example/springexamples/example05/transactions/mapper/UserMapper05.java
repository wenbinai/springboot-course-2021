package com.example.springexamples.example05.transactions.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springexamples.example05.transactions.entity.User05;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper05 extends BaseMapper<User05> {
}
