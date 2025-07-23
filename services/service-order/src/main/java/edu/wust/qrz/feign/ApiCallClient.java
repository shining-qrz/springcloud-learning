package edu.wust.qrz.feign;

import jakarta.annotation.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "xxx", url = "https://restapi.amap.com")
public interface ApiCallClient {

    @GetMapping("/v3/weather/weatherInfo")
    String getWeather(@RequestParam("key") String key,
                      @RequestParam("city") String city,
                      @RequestParam("extensions") String extensions);
}
