package com.piseth.java.school.phoneshop.phoneshop.service;


import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;

import java.util.List;


public interface BrandService {

    Brand create(Brand brand);
    Brand getById(Integer id);
    Brand update(Integer id, Brand brandUpdate);
    List<Brand> getBrands();
    List<Brand> getBrands(String name);

}
