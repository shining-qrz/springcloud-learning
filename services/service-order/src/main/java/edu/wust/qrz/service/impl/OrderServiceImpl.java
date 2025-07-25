package edu.wust.qrz.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import edu.wust.qrz.entity.Order;
import edu.wust.qrz.entity.Product;
import edu.wust.qrz.feign.ProductFeignClient;
import edu.wust.qrz.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    @Resource
    LoadBalancerClient loadBalancerClient;

    @Resource
    ProductFeignClient productFeignClient;

    @SentinelResource(value = "createOrder")
    @Override
    public Order createOrder(Long productId, Long userId) {
        Product product = productFeignClient.getProductById(productId);
        List<Product> products = new ArrayList<>();
        products.add(product);

        Order order = new Order();
        order.setId(0L);
        order.setUserid(userId);
        order.setAddress("xxx.xxx.xxx.xxxxxxx");
        order.setNickName("nickName");
        order.setProductList(products);

        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(product.getNumber()));
        order.setTotalAmount(totalAmount);

        return order;

    }

    //弃用
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

    //弃用
    private Product getProductByRemoteWithLoadBalancerClient(Long productId){
        //获取远程服务ip：port
        ServiceInstance instance = loadBalancerClient.choose("service-product");
        String host = instance.getHost();
        int port = instance.getPort();

        String url = "http://" + host + ":" + port + "/services/product/" + productId;

        log.info("远程请求：{}", url);

        return restTemplate.getForObject(url, Product.class);
    }

    //弃用
    //基于注解的负载均衡远程调用
    private Product getProductByRemoteWithLoadBalanced(Long productId){
        String url = "http://service-product/services/product/" + productId;
        return restTemplate.getForObject(url, Product.class);
    }
}
