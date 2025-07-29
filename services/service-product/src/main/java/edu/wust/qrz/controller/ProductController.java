package edu.wust.qrz.controller;

import edu.wust.qrz.entity.Product;
import edu.wust.qrz.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    ProductService productService;

    @GetMapping("/{productId}")
    public Product queryById(@PathVariable("productId") Long productId){
        System.out.println("Hello");
        return productService.queryById(productId);
    }
}
