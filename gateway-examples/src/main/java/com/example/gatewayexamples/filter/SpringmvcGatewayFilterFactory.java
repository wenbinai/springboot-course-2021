package com.example.gatewayexamples.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpringmvcGatewayFilterFactory extends AbstractGatewayFilterFactory<SpringmvcGatewayFilterFactory.Config> {
    public SpringmvcGatewayFilterFactory() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.debug("SpringmvcGatewayFilterFactory");
            return chain.filter(exchange);
        };
    }
    // 支持读取application.yml中配置
    public static class Config {
    }
}
