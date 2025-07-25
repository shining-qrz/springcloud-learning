package edu.wust.qrz.feign.fallback;

import edu.wust.qrz.entity.Product;
import edu.wust.qrz.feign.ProductFeignClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallBack implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("未知商品");
        product.setPrice(BigDecimal.valueOf(0));
        product.setNumber(0);

        return product;
    }
}
