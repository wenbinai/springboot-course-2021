package com.example.springmvcexamples.example09.gatway.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
}
