package com.piseth.java.school.phoneshop.phoneshop.service.util;

import com.piseth.java.school.phoneshop.phoneshop.dto.BrandDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;

public class Mapper {   // entities to dto / dto to entities

    public static Brand toBrand(BrandDTO dto){
        Brand brand = new Brand();
        //brand.setId(dto.getId());
        brand.setName(dto.getName());
        return brand;
    }
    public static BrandDTO toBrandDTO(Brand brand){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(brand.getName());
        return brandDTO;
    }

}
