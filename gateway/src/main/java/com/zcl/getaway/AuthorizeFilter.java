package com.zcl.getaway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 * 项目名称：cloud-demo
 * 描述：全局过滤器
 *
 * @author zhong
 * @date 2022-05-29 16:38
 */
// @Order(-1) // 执行的顺序，越小执行越前
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1、获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        // 2、获取参数中的authorizeation参数
        String auth = queryParams.getFirst("authorizeation");
        // 3、判断参数值是否为 admin
        if("admin".equals(auth)){
            // 4、是,放行
            return chain.filter(exchange);
        }
        // 5、否，拦截
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    /**
     * 全局过滤器的执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
