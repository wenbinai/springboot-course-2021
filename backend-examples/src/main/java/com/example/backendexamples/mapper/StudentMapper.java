package com.example.backendexamples.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendexamples.dto.StudentDTO;
import com.example.backendexamples.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    List<StudentDTO> list();
}