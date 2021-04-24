package com.example.backendexamples.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@TableName("course")
public class Course {
    private Long id;

    private String name;

    private Long teacherId;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;
}