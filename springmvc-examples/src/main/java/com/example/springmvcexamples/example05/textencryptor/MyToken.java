package com.example.springmvcexamples.example05.textencryptor;

import lombok.Data;

@Data
public class MyToken {
    public enum Role {
        USER, ADMIN
    }
    private Integer uid;
    private Role role;
}
