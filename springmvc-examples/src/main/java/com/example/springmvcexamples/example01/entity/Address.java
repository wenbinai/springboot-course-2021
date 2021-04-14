package com.example.springmvcexamples.example01.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Address {
    private Integer id;
    private String detail;
    private String comment;
    private User user;
    private LocalDateTime inertTime;

    public Address(Integer id, String detail, String comment, LocalDateTime inertTime) {
        this.id = id;
        this.detail = detail;
        this.comment = comment;
        this.inertTime = inertTime;
    }
}
