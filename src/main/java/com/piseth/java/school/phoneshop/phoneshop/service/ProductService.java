package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.entities.Product;

public interface ProductService {
    Product create(Product product);
    Product getById(Long id);
}