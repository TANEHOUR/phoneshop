package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductService {
    Product create(Product product);
    Product getById(Long id);

    Product getByModelIdandColorId(Long modelId, Long colorId);

    void importProduct(ProductImportDTO productImportDTO);
    void setSalePrice(Long productId, BigDecimal price);
    void validateStock(Long productId, Integer numberOfUnit);
    Map<Integer, String> uploadProduct(MultipartFile file);
}
