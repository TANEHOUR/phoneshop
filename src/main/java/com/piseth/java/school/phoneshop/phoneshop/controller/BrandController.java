package com.piseth.java.school.phoneshop.phoneshop.controller;


import com.piseth.java.school.phoneshop.phoneshop.dto.BrandDTO;
import com.piseth.java.school.phoneshop.phoneshop.dto.ModelDTO;
import com.piseth.java.school.phoneshop.phoneshop.dto.PageDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import com.piseth.java.school.phoneshop.phoneshop.entities.Model;
import com.piseth.java.school.phoneshop.phoneshop.mapper.BrandMapper;
import com.piseth.java.school.phoneshop.phoneshop.mapper.ModelEntityMapper;
import com.piseth.java.school.phoneshop.phoneshop.service.BrandService;
import com.piseth.java.school.phoneshop.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController // implicit @ResponseBody
@RequestMapping("brands")   // mean we need to handle view which one we need to show

public class BrandController {

    @Autowired
    private final BrandService brandService;
    private final ModelService modelService;
    private final ModelEntityMapper modelEntityMapper;


    @PreAuthorize("hasAnyAuthority('brand:write')")
    // Create / POST method
    @RequestMapping(method = RequestMethod.POST)    // use POST method in postman this method will run
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) { // @RequestBody mean request brandDTO body to show data for viewer
//        Brand brand = Mapper.toBrand(brandDTO);
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);
//        return ResponseEntity.ok(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @PreAuthorize("hasAnyAuthority('brand:read')")
    @GetMapping("{id}") // Read / GET method
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId) {    //@PathVariable if we write this will error integer is not present
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
    public ResponseEntity<?> update(@PathVariable("id") Long brandId, @RequestBody BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        Brand updateBrand = brandService.update(brandId, brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updateBrand));
    }
    @GetMapping("{id}/models")
    public ResponseEntity<?> getModelByBrand(@PathVariable("id") Long brandId) {
        List<Model> brands = modelService.getByBrand(brandId);
        List<ModelDTO> list = brands.stream()
                .map(modelEntityMapper::toModelDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

}
