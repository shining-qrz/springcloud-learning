package edu.wust.qrz.predicate;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Vip断言工厂
 */
@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {


    public VipRoutePredicateFactory(){super(Config.class);}



    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param", "value");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {

                ServerHttpRequest request = serverWebExchange.getRequest();
                String param = request.getQueryParams().getFirst(config.getParam());
                if(StringUtils.hasText(param)) {
                    return param.equals(config.value);
                }
                return false;
            }
        };
    }


    @Validated
    public static class Config{
        @NotNull
        private String param;
        @NotNull
        private String value;

        public Config(){
        }

        public String getParam(){
            return this.param;
        }

        public Config setParam(String param){
            this.param = param;
            return this;
        }

        public String getValue(){
            return this.value;
        }

        public Config setValue(String value){
            this.value = value;
            return this;
        }

    }
}
