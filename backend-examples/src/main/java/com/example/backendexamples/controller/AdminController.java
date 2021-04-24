package com.example.backendexamples.controller;

import com.example.backendexamples.dto.TeacherDTO;
import com.example.backendexamples.entity.Teacher;
import com.example.backendexamples.service.UserService;
import com.example.backendexamples.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "处理超管操作请求", tags = {"Authorization, Admin"})
@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    private UserService userService;

    @ApiOperation("添加教师")
    @PostMapping("teachers")
    public ResultVO addTeacher(@RequestBody TeacherDTO teacher) {
        Teacher t = userService.addTeacher(teacher);
        return ResultVO.success(Map.of("teacher", t));
    }
}
