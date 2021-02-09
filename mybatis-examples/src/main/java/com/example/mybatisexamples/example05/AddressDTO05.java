package com.example.mybatisexamples.example05;

import com.example.mybatisexamples.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressDTO05 {
    private Long id;
    private String detail;
    private User user;
    private LocalDateTime createTime;
}
