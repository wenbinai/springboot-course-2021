package com.example.springmvcexamples.example02.handlingexception.controller;


import com.example.springmvcexamples.example02.handlingexception.entity.User;
import com.example.springmvcexamples.example02.handlingexception.service.UserService02;
import com.example.springmvcexamples.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/example02/")
public class ExampleController02 {
    @Autowired
    private UserService02 userService02;

    @GetMapping("exception")
    public void getException() {
        userService02.readFile();
    }

    @PostMapping("login")
    public ResultVO login(@RequestBody User user) {
        if (!("BO".equals(user.getUserName()) && "123456".equals(user.getPassword()))) {
            return ResultVO.error(401, "用户名密码错误a");
        }
        return ResultVO.success(Map.of());
    }

}
