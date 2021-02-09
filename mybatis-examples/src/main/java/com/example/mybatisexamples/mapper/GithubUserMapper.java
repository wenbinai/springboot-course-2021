package com.example.mybatisexamples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisexamples.entity.GithubUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GithubUserMapper extends BaseMapper<GithubUser> {
}