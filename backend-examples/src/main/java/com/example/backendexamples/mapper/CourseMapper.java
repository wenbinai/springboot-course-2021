package com.example.backendexamples.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendexamples.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    List<Course> listBySid(long sid);

    default List<Course> listByTid(long tid) {
        return selectList(new LambdaQueryWrapper<Course>()
                .eq(Course::getTeacherId, tid));
    }

    default Course getCourse(long tid, long cid) {
        return selectOne(new LambdaQueryWrapper<Course>()
                .eq(Course::getId, cid)
                .eq(Course::getTeacherId, tid));
    }
}