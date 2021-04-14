package com.example.springmvcexamples.example04.passwordencoder.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private String userName;
    private String password;
}
