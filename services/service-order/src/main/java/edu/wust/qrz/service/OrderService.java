package edu.wust.qrz.service;

import edu.wust.qrz.entity.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
