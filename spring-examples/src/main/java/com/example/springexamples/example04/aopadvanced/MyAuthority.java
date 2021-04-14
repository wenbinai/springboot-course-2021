package com.example.springexamples.example04.aopadvanced;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyAuthority {
    MyAuthorityType[] value() default MyAuthorityType.USER;
    String key() default "";

    public enum MyAuthorityType {
        USER, ADMIN, SUPERADMIN
    }
}
