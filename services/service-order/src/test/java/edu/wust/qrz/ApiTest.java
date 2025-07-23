package edu.wust.qrz;

import edu.wust.qrz.feign.ApiCallClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiTest {

    @Resource
    ApiCallClient apiCallClient;

    @Test
    void weatherApiTest(){
        String weatherJosn = apiCallClient.getWeather("3e8e3414adb0baa3fcb6b8da62db2778", "421122", "base");
        System.out.println(weatherJosn);
    }
}
