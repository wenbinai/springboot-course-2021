package com.example.springexamples.example06.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCacheable {
    String value();

    String key() default "";

    TimeUnit timeUnit() default TimeUnit.MINUTES;

    long ttl() default 5;
}
