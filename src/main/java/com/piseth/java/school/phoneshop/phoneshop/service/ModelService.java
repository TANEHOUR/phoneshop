package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.entities.Model;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getByBrand(Long brandId);

    Model getById(Long id);
}
