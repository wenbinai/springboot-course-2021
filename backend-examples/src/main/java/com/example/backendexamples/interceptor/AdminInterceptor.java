package com.example.backendexamples.interceptor;

import com.example.backendexamples.common.MyException;
import com.example.backendexamples.common.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Integer.parseInt(String.valueOf(request.getAttribute("role"))) < Role.ADMIN) {
            throw new MyException(403, "无权限");
        }
        return true;
    }
}
