package com.example.springmvcexamples.example06.interceptor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User06 {
    private Long id;
    private String userName;
    private String password;
    private String role;
}
