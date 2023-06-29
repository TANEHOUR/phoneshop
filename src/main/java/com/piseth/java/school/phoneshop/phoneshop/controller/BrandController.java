package com.piseth.java.school.phoneshop.phoneshop.controller;


import com.piseth.java.school.phoneshop.phoneshop.dto.BrandDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import com.piseth.java.school.phoneshop.phoneshop.service.BrandService;
import com.piseth.java.school.phoneshop.phoneshop.service.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // implicit @ResponseBody
@RequestMapping("brands")

public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){ // @RequestBody submit from frontend
        Brand brand = Mapper.toBrand(brandDTO);
        brand = brandService.create(brand);
//        return ResponseEntity.ok(brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }

}
