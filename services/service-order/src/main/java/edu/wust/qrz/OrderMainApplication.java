package edu.wust.qrz;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager){
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                System.out.println("=====================================================");
                //获取nacos configService
                ConfigService configService = nacosConfigManager.getConfigService();
                //添加监听 addListener(String dataId, String group, Listener listener)
                configService.addListener("service-order.properties", "DEFAULT_GROUP", new Listener() {

                    //执行器 -- 运行在线程池中
                    @Override
                    public Executor getExecutor() {
                        return Executors.newFixedThreadPool(4);
                    }

                    //配置信息接收
                    @Override
                    public void receiveConfigInfo(String s) {
                        System.out.println("配置更新：");
                        System.out.println(s);
                        System.out.println("邮件发送...");
                    }
                });
            }
        };
    }
}
