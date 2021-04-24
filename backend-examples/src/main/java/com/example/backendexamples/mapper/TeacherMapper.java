package com.example.backendexamples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendexamples.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
}