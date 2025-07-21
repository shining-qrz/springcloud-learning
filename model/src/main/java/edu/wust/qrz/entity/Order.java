package edu.wust.qrz.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private BigDecimal totalAmount;
    private Long userid;
    private String nickName;
    private String address;
    private List<Product> productList;
}
