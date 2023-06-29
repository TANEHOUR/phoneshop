package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import com.piseth.java.school.phoneshop.phoneshop.repository.BrandRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service    // can use component but not recommend

public class BrandServiceImpl implements BrandService {

    @Autowired  // it'll create obj for us
    private BrandRepository brandRepository;    // call method to save data

    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

}
