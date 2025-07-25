package edu.wust.qrz.config;

import feign.Logger;
import feign.RetryableException;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {

    //日志级别
    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }

    //默认重试机制
    @Bean
    Retryer retryer(){
        return new Retryer.Default();
    }
}
