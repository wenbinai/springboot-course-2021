package com.example.backendexamples.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String clazz;
    private String number;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
