package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.repository.BrandRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service    // can use component but not recommend

public class BrandServiceImpl implements BrandService {

    @Autowired  // it'll create obj for us
    private BrandRepository brandRepository;    // call method to save data

    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand getById(Integer id) {
        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand", id));
/*                () -> new ApiException(HttpStatus.NOT_FOUND, String.format("Brand with id = %d not found.", id))); // java version 6, 7
        orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand with id = %d not found.".formatted(id))); // java version 8*/
    }

    @Override
    public Brand update(Integer id, Brand brandUpdate) {
        Brand brand = getById(id);
        brand.setName(brandUpdate.getName());
        return brandRepository.save(brand); // if already have id it'll update
        // and when don't have id it'll create
    }

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getBrands(String name) {
//        return brandRepository.findByNameLike("%" + name + "%");
        return brandRepository.findByNameContaining(name);

    }

}
