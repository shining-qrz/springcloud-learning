package edu.wust.qrz.controller;

import edu.wust.qrz.entity.Order;
import edu.wust.qrz.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope //自动刷新配置属性
@RestController
@RequestMapping("/services/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @Value("${order.timeout}")
    String orderTimeout;

    @Value("${order.auto-confirm}")
    String orderAutoConfirm;

    @GetMapping("/config")
    public String getConfig(){
        return  "order-timeout: " + orderTimeout + "；order-auto-confirm: " + orderAutoConfirm;
    }

    @PostMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId, @RequestParam("userId") Long userId){
        return orderService.createOrder(productId, userId);
    }
}
