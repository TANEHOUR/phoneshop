package com.piseth.java.school.phoneshop.phoneshop.utils;

import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.entities.ProductImportHistory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReportTestHelp {
    private static Product product1 = Product.builder()
            .id(1l)
            .name("iPhone 14 pro")
            .build();
    private static Product product2 = Product.builder()
            .id(2l)
            .name("Samsung Galaxy J7")
            .build();
    private static Product product3 = Product.builder()
            .id(3l)
            .name("Asus ROG6")
            .build();

    public static List<Product> geProducts(){
        return List.of(product1, product2);
    }

    public static List<ProductImportHistory> getProductImportHistories(){

        ProductImportHistory history1 = ProductImportHistory.builder()
                .product(product1)
                .importUnit(15)
                .pricePerUnit(BigDecimal.valueOf(1200))
                .dateImport(LocalDateTime.of(2023, 7, 27, 8, 0))
                .build();

        ProductImportHistory history2 = ProductImportHistory.builder()
                .product(product2)
                .importUnit(10)
                .pricePerUnit(BigDecimal.valueOf(1000))
                .dateImport(LocalDateTime.of(2023, 8, 1, 8, 0))
                .build();

        ProductImportHistory history3 = ProductImportHistory.builder()
                .product(product1)
                .importUnit(10)
                .pricePerUnit(BigDecimal.valueOf(1000))
                .dateImport(LocalDateTime.of(2023, 6, 18, 12, 0))
                .build();

        return List.of(history1, history2, history3);
    }
}
