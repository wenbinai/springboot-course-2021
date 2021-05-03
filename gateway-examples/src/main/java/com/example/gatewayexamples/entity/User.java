package com.example.gatewayexamples.entity;

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
