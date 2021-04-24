package com.example.backendexamples;

import com.example.backendexamples.dto.StudentDTO;
import com.example.backendexamples.dto.TeacherDTO;
import com.example.backendexamples.entity.User;
import com.example.backendexamples.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        String number = "1001";
        User u = userService.getUser(number);
        log.debug(u.getName());
    }

    @Test
    public void test2() {
        TeacherDTO teacherDTO = TeacherDTO.builder()
                .name("BO")
                .number("1002")
                .title("讲师")
                .build();
        userService.addTeacher(teacherDTO);
    }

    @Test
    public void test3() {
        long cid = 1384896304762638337L;
        StudentDTO s1 = StudentDTO.builder()
                .name("Jack")
                .clazz("软件1班")
                .number("204600010001")
                .build();
        StudentDTO s2 = StudentDTO.builder()
                .name("Tom")
                .clazz("软件1班")
                .number("204600010002")
                .build();
        StudentDTO s3 = StudentDTO.builder()
                .name("Peter")
                .clazz("软件1班")
                .number("204600010003")
                .build();
        StudentDTO s4 = StudentDTO.builder()
                .name("Mean")
                .clazz("软件1班")
                .number("204600010004")
                .build();
        userService.addStudents(List.of(s1, s2, s3, s4), cid);
    }

    @Test
    public void test4() {
        List<StudentDTO> studentDTOS = userService.listStudents();
        log.debug(studentDTOS.getClass().getName());
        for (StudentDTO s : studentDTOS) {
            log.debug(s.getName());
        }
    }
}
