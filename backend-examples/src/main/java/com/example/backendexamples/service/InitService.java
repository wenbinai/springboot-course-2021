package com.example.backendexamples.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backendexamples.entity.User;
import com.example.backendexamples.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class InitService implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        // id字段必然存在，数量为0表示用户名表为空。
        // 初始化管理员用户，赋权限值
        int count = userMapper.selectCount(new QueryWrapper<User>().select("id"));
        if (count == 0) {
            User user = User.builder()
                    .name("admin")
                    .number("admin")
                    .password(encoder.encode("admin"))
                    .role(9)
                    .build();
            userMapper.insert(user);
        }
    }
}
