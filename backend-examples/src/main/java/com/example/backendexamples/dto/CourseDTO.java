package com.example.backendexamples.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long cid;
    List<StudentDTO> students;
}
