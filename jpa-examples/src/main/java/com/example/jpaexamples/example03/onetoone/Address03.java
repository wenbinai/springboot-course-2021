package com.example.jpaexamples.example03.onetoone;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Address03 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;
    @OneToOne
    @JoinColumn(unique = true)
    private User03 user;
}
