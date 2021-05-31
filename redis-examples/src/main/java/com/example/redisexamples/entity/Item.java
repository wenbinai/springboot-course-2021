package com.example.redisexamples.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private Long id;
    private int total;
}
