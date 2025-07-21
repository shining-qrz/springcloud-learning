package edu.wust.qrz.service.impl;

import edu.wust.qrz.entity.Product;
import edu.wust.qrz.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product queryById(Long productId) {
        Product product = new Product();

        product.setId(productId);
        product.setName("测试商品0");
        product.setPrice(BigDecimal.valueOf(99.99));
        product.setNumber(1);

        return product;
    }
}
