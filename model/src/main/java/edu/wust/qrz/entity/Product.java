package edu.wust.qrz.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private BigDecimal price;
    private String Name;
    private int number;
}
