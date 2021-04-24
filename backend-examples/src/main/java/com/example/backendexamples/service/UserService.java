package com.example.backendexamples.service;

import com.example.backendexamples.common.Role;
import com.example.backendexamples.dto.StudentDTO;
import com.example.backendexamples.dto.TeacherDTO;
import com.example.backendexamples.entity.Student;
import com.example.backendexamples.entity.StudentCourse;
import com.example.backendexamples.entity.Teacher;
import com.example.backendexamples.entity.User;
import com.example.backendexamples.mapper.StudentCourseMapper;
import com.example.backendexamples.mapper.StudentMapper;
import com.example.backendexamples.mapper.TeacherMapper;
import com.example.backendexamples.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;

    public User getUser(String number) {
        return userMapper.getByNumber(number);
    }

    public void updatePassword(long uid, String pwd) {
        userMapper.updateById(User.builder().id(uid).password(encoder.encode(pwd)).build());
    }

    public Teacher addTeacher(TeacherDTO teacher) {
        User u = User.builder()
                .name(teacher.getName())
                .number(teacher.getNumber())
                .password(encoder.encode(teacher.getNumber()))
                .role(Role.TEACHER)
                .build();
        userMapper.insert(u);

        // user保存后，会获取到id值。作为teacher表的id。共用主键
        Teacher t = Teacher.builder()
                .id(u.getId())
                .title(teacher.getTitle())
                .build();
        teacherMapper.insert(t);
        return t;
    }
    @CacheEvict(value = "students", allEntries = true)
    public void addStudents(List<StudentDTO> students, long cid) {
        for (StudentDTO sDTO : students) {
            sDTO.setId(null);
            User u = userMapper.getByNumber(sDTO.getNumber());
            // 学生已经存在
            if (u != null) {
                StudentCourse sc = studentCourseMapper.getStudentCourse(u.getId(), cid);
                // 学生与课程已经建立关系
                if (sc != null) {
                    continue;
                }
                // 学生存在，但未与课程建立关系
                sc = StudentCourse.builder()
                        .studentId(u.getId())
                        .courseId(cid)
                        .build();
                studentCourseMapper.insert(sc);
                continue;
            }
            // 学生不存在
            u = User.builder()
                    .name(sDTO.getName())
                    .role(Role.STUDENT)
                    .number(sDTO.getNumber())
                    .password(encoder.encode(sDTO.getNumber()))
                    .build();
            userMapper.insert(u);

            Student st = Student.builder()
                    .clazz(sDTO.getClazz())
                    .id(u.getId())
                    .build();
            studentMapper.insert(st);
            // 与课程建立关系
            StudentCourse sc = StudentCourse.builder()
                    .studentId(u.getId())
                    .courseId(cid)
                    .build();
            studentCourseMapper.insert(sc);
        }
    }
    @Cacheable(value = "students")
    public List<StudentDTO> listStudents() {
        log.debug("listStudents()");
        return studentMapper.list();
    }
}
