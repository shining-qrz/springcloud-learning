package edu.wust.qrz.filter;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Component
public class XResponseTokenGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    ServerHttpResponse response = exchange.getResponse();
                    // 在响应头中添加自定义的X-Response-Token
                    String responseValue = "";
                    if("uuid".equalsIgnoreCase(config.getValue()))
                        responseValue = UUID.randomUUID().toString();

                    if("jwt".equalsIgnoreCase(config.getValue()))
                        responseValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                                "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                                "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

                    response.getHeaders().add(config.getName(), responseValue);
                }));
            }
        };
    }
}
