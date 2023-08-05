package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;

import java.math.BigDecimal;

public interface ProductService {
    Product create(Product product);
    Product getById(Long id);

    void importProduct(ProductImportDTO productImportDTO);
    void setSalePrice(Long productId, BigDecimal price);
    void validateStock(Long productId, Integer numberOfUnit);
}
