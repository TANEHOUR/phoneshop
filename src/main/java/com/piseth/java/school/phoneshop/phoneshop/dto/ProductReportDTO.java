package com.piseth.java.school.phoneshop.phoneshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductReportDTO {
    private Long productId;
    private String productName;
    private Integer unit;
    private BigDecimal totalAmount;
}
