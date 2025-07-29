package edu.wust.qrz.filter.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器，记录请求和响应的日志
 * 该过滤器会在每个请求开始时记录开始时间，并在请求结束时记录结束时间和耗时
 * 通过实现Ordered接口，可以设置过滤器的执行顺序
 * 该过滤器的执行顺序为0，表示它会在所有其他过滤器之前执行
 */
@Slf4j
@Component
public class RTGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String uri = request.getURI().toString();
        long startTime = System.currentTimeMillis();
        log.info("请求【{}】开始，开始时间：{}", uri, startTime);

        return chain.filter(exchange).doFinally(result -> {
            long endTime = System.currentTimeMillis();
            log.info("请求【{}】结束，结束时间：{}，耗时：{}ms", uri, endTime, (endTime - startTime));
        });

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
