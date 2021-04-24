package com.example.backendexamples.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendexamples.entity.Course;
import com.example.backendexamples.entity.StudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    default StudentCourse getStudentCourse(long sid, long cid) {
        return selectOne(new LambdaQueryWrapper<StudentCourse>()
                .eq(StudentCourse::getCourseId, cid)
                .eq(StudentCourse::getStudentId, sid));
    }
}