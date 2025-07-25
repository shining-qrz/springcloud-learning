package edu.wust.qrz.feign;

import edu.wust.qrz.entity.Product;
import edu.wust.qrz.feign.fallback.ProductFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product", fallback = ProductFeignClientFallBack.class )
public interface ProductFeignClient {

    @GetMapping("/services/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
