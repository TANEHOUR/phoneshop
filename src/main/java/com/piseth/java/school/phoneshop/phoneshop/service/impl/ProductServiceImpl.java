package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {

        String name ="%s %s"
                .formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }
}
