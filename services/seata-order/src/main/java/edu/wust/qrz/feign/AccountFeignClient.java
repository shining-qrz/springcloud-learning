package edu.wust.qrz.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-account")
public interface AccountFeignClient {

    @GetMapping("/debit")
    String debit(@RequestParam("userId") String userId,
                        @RequestParam("money") int money);
}
