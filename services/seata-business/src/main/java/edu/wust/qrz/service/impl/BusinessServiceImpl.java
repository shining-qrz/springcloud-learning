package edu.wust.qrz.service.impl;


import edu.wust.qrz.feign.OrderFeignClient;
import edu.wust.qrz.feign.StorageFeignClient;
import edu.wust.qrz.service.BusinessService;
import jakarta.annotation.Resource;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;


@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    OrderFeignClient orderFeignClient;

    @Resource
    StorageFeignClient storageFeignClient;

    @GlobalTransactional
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {

        //扣减库存
        storageFeignClient.deduct(commodityCode, orderCount);

        //创建订单
        orderFeignClient.create(userId, commodityCode, orderCount);
    }
}
