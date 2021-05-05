package com.example.springexamples.example06.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class RedisAspect {
    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private ObjectMapper mapper;

    @Around("@annotation(redAn)")
    public Object redisCache(ProceedingJoinPoint joinPoint, RedisCacheable redAn) throws Throwable {
        Method method = getMethod(joinPoint);
        String redKey = redAn.value();
        String key = redAn.key();
        // 如果注解key值不为空，追加生成redis key值
        if (!key.equals("")) {
            String id = getSpEL(redAn.key(), method, joinPoint.getArgs());
            redKey = redKey + "::" + id;
        }
        String redisValue = template.opsForValue().get(redKey);
        Object returning;
        if (redisValue != null) {
            // redis中存在，反序列化
            returning = mapper.readValue(redisValue, mapper.constructType(method.getGenericReturnType()));
        } else {
            // 不存在，执行被切方法，将方法返回结果序列化，置于redis
            returning = joinPoint.proceed();
            long time = redAn.ttl();
            String json = mapper.writeValueAsString(returning);
            if (time == 0) {
                template.opsForValue().set(redKey, json);
            } else {
                template.opsForValue().set(redKey, json, time, redAn.timeUnit());
            }
        }
        return returning;
    }

    @AfterReturning(value = "@annotation(redAn)", returning = "returning")
    public void redisPut(JoinPoint joinPoint, RedisCachePut redAn, Object returning) throws Throwable {
        String redKey = redAn.value();
        String key = redAn.key();
        if (!key.equals("")) {
            String id = getSpEL(redAn.key(), getMethod(joinPoint), joinPoint.getArgs());
            redKey = redKey + "::" + id;
        }
        long time = redAn.ttl();
        String json = mapper.writeValueAsString(returning);
        if (time == 0) {
            template.opsForValue().set(redKey, json);
        } else {
            template.opsForValue().set(redKey, json, time, redAn.timeUnit());
        }
    }

    @AfterReturning(value = "@annotation(redAn)")
    public void redisEvict(JoinPoint joinPoint, RedisCacheEvict redAn) {
        String redKey = redAn.value();
        String key = redAn.key();
        if (!key.equals("")) {
            String id = getSpEL(redAn.key(), getMethod(joinPoint), joinPoint.getArgs());
            redKey = redKey + "::" + id;
        }
        template.delete(redKey);
    }

    // 获取别切方法对象
    private Method getMethod(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
    }

    private final SpelExpressionParser parser = new SpelExpressionParser();
    private final LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
    private final StandardEvaluationContext context = new StandardEvaluationContext();

    // 获取key中SpEL表达式的值
    private String getSpEL(String key, Method method, Object[] args) {
        String[] paraNameArr = u.getParameterNames(method);
        assert paraNameArr != null;
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

}
