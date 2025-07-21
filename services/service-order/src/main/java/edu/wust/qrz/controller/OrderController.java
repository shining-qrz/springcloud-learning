package edu.wust.qrz.controller;

import edu.wust.qrz.entity.Order;
import edu.wust.qrz.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId, @RequestParam("userId") Long userId){
        return orderService.createOrder(productId, userId);
    }
}
