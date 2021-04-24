package com.example.backendexamples.controller;

import com.example.backendexamples.common.Role;
import com.example.backendexamples.entity.Course;
import com.example.backendexamples.entity.User;
import com.example.backendexamples.service.CourseService;
import com.example.backendexamples.service.UserService;
import com.example.backendexamples.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "处理用户通用操作请求", tags = "Authorization")
@Slf4j
@RestController
@RequestMapping("/api/common/")
public class CommonController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @ApiOperation("登录后首页信息，加载用户的全部课程")
    @GetMapping("home")
    public ResultVO getCourses(@RequestAttribute("uid") long uid, @RequestAttribute("role") int role) {
        List<Course> courses = role == Role.TEACHER
                ? courseService.listCoursesByTid(uid)
                : courseService.listCoursesBySid(uid);
        return ResultVO.success(Map.of("courses", courses));
    }
    @ApiOperation("修改密码")
    @PatchMapping("password")
    public ResultVO patchPassword(@RequestBody Map<String, String> map, @RequestAttribute("uid") long uid) {
        userService.updatePassword(uid, map.get("password"));
        return ResultVO.success(Map.of());
    }

}
