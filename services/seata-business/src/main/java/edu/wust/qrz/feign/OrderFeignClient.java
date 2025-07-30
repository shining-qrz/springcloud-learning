package edu.wust.qrz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-order")
public interface OrderFeignClient {

    @GetMapping("/create")
    String create(@RequestParam("userId") String userId,
                         @RequestParam("commodityCode") String commodityCode,
                         @RequestParam("count") int orderCount);
}
