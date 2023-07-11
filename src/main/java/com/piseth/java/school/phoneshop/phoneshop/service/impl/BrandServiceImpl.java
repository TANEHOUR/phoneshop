package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.repository.BrandRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.BrandService;
import com.piseth.java.school.phoneshop.phoneshop.service.util.PageUtil;
import com.piseth.java.school.phoneshop.phoneshop.spacification.BrandFilter;
import com.piseth.java.school.phoneshop.phoneshop.spacification.BrandSpeci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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

    /*    @Override
        public List<Brand> getBrands() {
            return brandRepository.findAll();

        }
            @Override
        public List<Brand> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();
        if (params.containsKey("name")){
            String name = params.get("name");
            brandFilter.setName(name);
        }
        if (params.containsKey("id")){
            String id = params.get("id");
            brandFilter.setId(Integer.valueOf(id));
        }

        BrandSpeci brandSpeci = new BrandSpeci(brandFilter);

        return brandRepository.findAll(brandSpeci);
    }
        */
    @Override
    public Page<Brand> getBrands(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();
        if (params.containsKey("name")) {
            String name = params.get("name");
            brandFilter.setName(name);
        }
        if (params.containsKey("id")) {
            String id = params.get("id");
            brandFilter.setId(Integer.valueOf(id));
        }
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtil.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        BrandSpeci brandSpeci = new BrandSpeci(brandFilter);

        Pageable pageable = PageUtil.getPageable(0, 0);

        Page<Brand> page = brandRepository.findAll(brandSpeci, pageable);
        return page;
    }

    @Override
    public List<Brand> getBrands(String name) {
//        return brandRepository.findByNameLike("%" + name + "%");
        return brandRepository.findByNameContaining(name);

    }

}
