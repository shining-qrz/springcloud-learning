package edu.wust.qrz.controller;

import edu.wust.qrz.config.properties.OrderProperties;
import edu.wust.qrz.entity.Order;
import edu.wust.qrz.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

//@RefreshScope //自动刷新配置属性
@RestController
@RequestMapping("/api/services/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @Resource
    OrderProperties orderProperties;

//    @Value("${order.timeout}")
//    String orderTimeout;
//
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;

    @GetMapping("/config")
    public String getConfig(){
        return  "order-timeout: " + orderProperties.getTimeOut() + "；order-auto-confirm: " + orderProperties.getAutoConfirm();
    }

    @PostMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId, @RequestParam("userId") Long userId){
        return orderService.createOrder(productId, userId);
    }
}
