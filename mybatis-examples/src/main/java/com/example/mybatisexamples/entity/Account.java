package com.example.mybatisexamples.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@TableName("account")
public class Account {
    private Long id;

    private String name;

    private Float balance;

    @Version
    private Integer version;
}