package com.example.redisexamples.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable{
    private Long id;
    private String name;
    private String detail;
}
