package edu.wust.qrz.config;

import feign.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {

    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}
