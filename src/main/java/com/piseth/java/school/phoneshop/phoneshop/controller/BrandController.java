package com.piseth.java.school.phoneshop.phoneshop.controller;


import com.piseth.java.school.phoneshop.phoneshop.dto.BrandDTO;
import com.piseth.java.school.phoneshop.phoneshop.dto.PageDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import com.piseth.java.school.phoneshop.phoneshop.mapper.BrandMapper;
import com.piseth.java.school.phoneshop.phoneshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController // implicit @ResponseBody
@RequestMapping("brands")   // mean we need to handle view which one we need to show

public class BrandController {

    @Autowired
    private BrandService brandService;

    // Create / POST method
    @RequestMapping(method = RequestMethod.POST)    // use POST method in postman this method will run
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) { // @RequestBody mean request brandDTO body to show data for viewer
//        Brand brand = Mapper.toBrand(brandDTO);
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);
//        return ResponseEntity.ok(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}") // Read / GET method
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId) {    //@PathVariable if we write this will error integer is not present
        Brand brand = brandService.getById(brandId);                              //@PathVariable("id") the same as GetMapping it'll work
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    /*    @GetMapping()
        public ResponseEntity<?> getBrand(){
            List<BrandDTO> list = brandService.getBrands()
                    .stream()
                    .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(list);
        }
        @GetMapping("filter")
        public ResponseEntity<?> getBrand(@RequestParam("name") String name){
            List<BrandDTO> list = brandService.getBrands(name)
                    .stream()
                    .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(list);
        }*/
    @GetMapping
    public ResponseEntity<?> getBrand(@RequestParam Map<String, String> params) {
        Page<Brand> page = brandService.getBrands(params);
        PageDTO pageDTO = new PageDTO(page);
/*        List<BrandDTO> list = brandService.getBrands(params)
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
                .collect(Collectors.toList());*/
        return ResponseEntity.ok(pageDTO);
    }

    @PutMapping("{id}") // Update / PUT method
    public ResponseEntity<?> update(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        Brand updateBrand = brandService.update(brandId, brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updateBrand));
    }


}
