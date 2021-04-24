package com.example.backendexamples.configuration;

import com.example.backendexamples.interceptor.AdminInterceptor;
import com.example.backendexamples.interceptor.LoginInterceptor;
import com.example.backendexamples.interceptor.TeacherInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private TeacherInterceptor teacherInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
        .excludePathPatterns("/api/login", "/api/welcome");
        registry.addInterceptor(teacherInterceptor)
                .addPathPatterns("/api/teacher/**");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");
    }

}
