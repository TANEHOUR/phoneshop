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
    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId){    //@PathVariable if we write this will error integer is not present
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO){
        Brand brand = Mapper.toBrand(brandDTO);
        Brand updateBrand = brandService.update(brandId, brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(updateBrand));
    }

}
