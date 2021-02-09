package com.example.mybatisexamples.example04;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AddressMapper04Test {
    @Autowired
    private AddressMapper04 addressMapper04;
    @Test
    public void list_test() {
        for (AddressDTO04 a : addressMapper04.list("1201")) {
            log.debug("{}", a);
        }
    }
    @Test
    public void list_test2() {
        for (AddressDTO04 a : addressMapper04.list2("1201")) {
            log.debug("{}", a);
        }
    }
}
