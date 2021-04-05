package com.example.jpaexamples.example04;

import com.example.jpaexamples.example04.manytomany.Course04;
import com.example.jpaexamples.example04.manytomany.Elective04;
import com.example.jpaexamples.example04.manytomany.Student04;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class ManyToManyTest {
    @Autowired
    private EntityManager manager;

    @Test
    public void test_init() {
        // 初始学生1
        Student04 s = new Student04();
        s.setName("BO");
        manager.persist(s);
        // 初始化学生2
        Student04 s2 = new Student04();
        s2.setName("SUN");
        manager.persist(s2);
        //初始化课程1
        Course04 c = new Course04();
        c.setName("Web框架");
        manager.persist(c);
    }

    @Test
    public void  test_rel() {
        // ID位1的学生，选择了ID为1的课程
        Student04 s = manager.find(Student04.class, 2);
        Course04 c = manager.find(Course04.class, 1);
        Elective04 elective = new Elective04();
        elective.setDetail("qqqqq");
        elective.setStudent(s);
        elective.setCourse(c);
        manager.persist(elective);
    }
}
