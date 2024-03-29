package com.piseth.java.school.phoneshop.phoneshop.service;


import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface BrandService {

    Brand create(Brand brand);

    Brand getById(Long id);

    Brand update(Long id, Brand brandUpdate);

    //    List<Brand> getBrands();
    List<Brand> getBrands(String name);
//    List<Brand> getBrands(Map<String, String> params);

    Page<Brand> getBrands(Map<String, String> params);

}
