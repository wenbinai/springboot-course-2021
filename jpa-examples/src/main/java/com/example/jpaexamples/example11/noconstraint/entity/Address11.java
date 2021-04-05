package com.example.jpaexamples.example11.noconstraint.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
// 不使用物理外键，也应加索引
//@Table(indexes = {@Index(columnList = "user_id")})
public class Address11 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;
    @ManyToOne
    // 无外键约束
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private User11 user;
}
