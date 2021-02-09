package com.example.mybatisexamples.example05;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AddressMapper05Test {
    @Autowired
    private AddressMapper05 addressMapper05;
    @Test
    public void list_xml_test() {
        for (AddressDTO05 aDTO : addressMapper05.listAddressDTOs("1201")) {
            log.debug("{}/{}", aDTO.getId(), aDTO.getUser().getName());
        }
    }
}
