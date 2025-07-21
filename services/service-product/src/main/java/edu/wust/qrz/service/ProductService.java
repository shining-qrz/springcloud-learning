package edu.wust.qrz.service;

import edu.wust.qrz.entity.Product;

public interface ProductService {
    Product queryById(Long productId);
}
