package com.example.springmvcexamples.example02.handlingexception.service;

import com.example.springmvcexamples.example02.handlingexception.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class UserService02 {
    public void readFile() {
        try {
            Files.readString(Path.of("A:/aa.aa"));
        } catch (IOException e) {
            throw new MyException(500, "读取文件错误！" + e.getMessage());
        }
    }
}
