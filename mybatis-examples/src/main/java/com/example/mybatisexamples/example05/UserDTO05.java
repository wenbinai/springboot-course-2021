package com.example.mybatisexamples.example05;

import com.example.mybatisexamples.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO05 {
    private Long id;
    private String name;
    private String company;
    private List<Address> addresses;
}
