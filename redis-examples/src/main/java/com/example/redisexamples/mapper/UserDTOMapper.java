package com.example.redisexamples.mapper;

import com.example.redisexamples.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserDTOMapper {
    public UserDTO get(Long uid) {
        return UserDTO.builder().id(1L).count(100).build();
    }
}
