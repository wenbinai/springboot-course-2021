package com.example.backendexamples.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backendexamples.entity.Course;
import com.example.backendexamples.entity.StudentCourse;
import com.example.backendexamples.mapper.CourseMapper;
import com.example.backendexamples.mapper.StudentCourseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public void addCourse(Course course, long uid) {
        course.setId(null);
        course.setTeacherId(uid);
        courseMapper.insert(course);
    }

    public List<Course> listCoursesByTid(long uid) {
        return courseMapper.listByTid(uid);
    }

    public List<Course> listCoursesBySid(long sid) {
        return courseMapper.listBySid(sid);
    }

    public Course getCourse(long tid, long cid) {
        return courseMapper.getCourse(tid, cid);
    }
}
