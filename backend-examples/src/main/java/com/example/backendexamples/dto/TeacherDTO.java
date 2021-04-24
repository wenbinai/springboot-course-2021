package com.example.backendexamples.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private String name;
    private String number;
    private String title;
}
