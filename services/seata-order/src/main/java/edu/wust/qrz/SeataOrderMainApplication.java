package edu.wust.qrz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients(basePackages = "edu.wust.qrz.feign")
@EnableTransactionManagement
@MapperScan("edu.wust.qrz.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class SeataOrderMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApplication.class, args);
    }


}
