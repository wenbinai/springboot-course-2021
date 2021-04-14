package com.example.springexamples.example05.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCache {
    String value();
    String key() default "";
    TimeUnit timeUnit() default TimeUnit.MINUTES;
    long ttl() default 0;
}
