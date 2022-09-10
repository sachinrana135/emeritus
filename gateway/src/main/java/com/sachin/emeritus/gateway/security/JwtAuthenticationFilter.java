package com.sachin.emeritus.gateway.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import com.sachin.emeritus.commonlib.security.JwtUtil;
import com.sachin.emeritus.commonlib.security.exceptions.JwtTokenMalformedException;
import com.sachin.emeritus.commonlib.security.exceptions.JwtTokenMissingException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.support.Configurable;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RefreshScope
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<AuthFilterConfig> {

    @Autowired
    private RouterValidator routerValidator;

    public JwtAuthenticationFilter() {
        super(AuthFilterConfig.class);
    }

    @Override
    public GatewayFilter apply(AuthFilterConfig config) {
        return ((exchange, chain) -> {
            if (routerValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing Authorisation Header");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                try {
                    JwtUtil.validateToken(authHeader);
                } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
                    // e.printStackTrace();

                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.BAD_REQUEST);

                    return response.setComplete();
                }
            }

            return chain.filter(exchange);
        });
    }

}