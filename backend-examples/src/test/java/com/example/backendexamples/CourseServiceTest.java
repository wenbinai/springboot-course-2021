package com.example.backendexamples;

import com.example.backendexamples.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;
    @Test
    public void test() {
        int a = courseService.listCoursesBySid(1L).size();
        log.debug("{}", a);
    }
}
