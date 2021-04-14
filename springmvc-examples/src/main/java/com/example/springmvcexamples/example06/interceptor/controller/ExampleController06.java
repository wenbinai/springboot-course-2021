package com.example.springmvcexamples.example06.interceptor.controller;

import com.example.springmvcexamples.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/example06/")
public class ExampleController06 {

    @GetMapping("admin/welcome")
    public ResultVO getWelcome() {
        return ResultVO.success(Map.of("msg", "message"));
    }
}
