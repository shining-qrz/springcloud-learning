package edu.wust.qrz.service.impl;

import edu.wust.qrz.entity.Order;
import edu.wust.qrz.entity.Product;
import edu.wust.qrz.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    DiscoveryClient discoveryClient;

    @Resource
    RestTemplate restTemplate;

    @Override
    public Order createOrder(Long productId, Long userId) {
        Product product = getProductByRemote(productId);
        List<Product> products = new ArrayList<>();
        products.add(product);

        Order order = new Order();
        order.setId(0L);
        order.setUserid(userId);
        order.setAddress("xxx.xxx.xxx.xxxxxxx");
        order.setNickName("nickName");
        order.setProductList(products);

        //TODO 计算总金额
        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(product.getNumber()));
        order.setTotalAmount(totalAmount);

        return order;

    }

    private Product getProductByRemote(Long productId){
        //获取远程服务ip：port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        String host = instance.getHost();
        int port = instance.getPort();

        String url = "http://" + host + ":" + port + "/services/product/" + productId;

        log.info("远程请求：{}", url);

        return restTemplate.getForObject(url, Product.class);
    }
}
