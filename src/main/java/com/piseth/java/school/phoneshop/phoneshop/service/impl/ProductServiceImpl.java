package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.entities.ProductImportHistory;
import com.piseth.java.school.phoneshop.phoneshop.exception.ApiException;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.mapper.ProductMapper;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {

        String name = "%s %s"
                .formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    public void importProduct(ProductImportDTO productImportDTO) {
        // update available product unit
        //productRepository.getById(productImportDTO.getProductId());
        Product product = getById(productImportDTO.getProductId());
        //Integer availableUnit = product.getAvailableUnit() + productImportDTO.getImportUnit();
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null){
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + productImportDTO.getImportUnit());
        productRepository.save(product);

        //save product import history
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(productImportDTO, product);
        productImportHistoryRepository.save(productImportHistory);

    }
}
