package edu.wust.qrz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@MapperScan("edu.wust.qrz.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class SeataStorageMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMainApplication.class, args);
    }
}
