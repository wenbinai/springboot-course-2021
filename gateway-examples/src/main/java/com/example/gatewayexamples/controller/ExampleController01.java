package com.example.gatewayexamples.controller;

import com.example.gatewayexamples.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/")
public class ExampleController01 {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Value("${springmvc-server}")
    private String springmvcBaseUrl;

    // 请求同步处理
    @GetMapping("users")
    public String getUsersRestTemplate() {
        String s = restTemplate.getForObject(springmvcBaseUrl + "example09/users", String.class);
        String s2 = restTemplate.getForObject(springmvcBaseUrl + "example09/users", String.class);
        log.debug("{}", s);
        return s + s2;
    }

    // 请求异步非阻塞处理
    @GetMapping("users/{uid}")
    public Mono<User> getUser(@PathVariable long uid) {
        return webClientBuilder.build()
                .get()
                .uri(springmvcBaseUrl + "example09/users/" + uid)
                .retrieve()// 发出请求
                .bodyToMono(User.class); // 将响应体反序列化
    }

    // 泛型的类型声明
    @GetMapping("users-async")
    public Mono<List<User>> getUsers() {
        List<User> users = new ArrayList<>();
        Mono<List<User>> u1 = webClientBuilder.build()
                .get()
                .uri(springmvcBaseUrl + "example09/users")
                .retrieve()// 发出请求
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {})
                .doOnSuccess(users::addAll);
        Mono<List<User>> u2 = webClientBuilder.build()
                .get()
                .uri(springmvcBaseUrl + "example09/users")
                .retrieve()// 发出请求
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {})
                .doOnSuccess(users::addAll);
        // 等待全部异步结果返回。类似CompletableFuture的使用
        return Mono.when(u1, u2).thenReturn(users);
    }

    @PostMapping("users")
    public Mono<User> postUser(@RequestBody User user) {
        return webClientBuilder.build()
                .post()
                .uri(springmvcBaseUrl + "example09/users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);
    }
}
