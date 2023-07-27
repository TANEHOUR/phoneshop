package com.piseth.java.school.phoneshop.phoneshop.controller;


import com.piseth.java.school.phoneshop.phoneshop.dto.ProductDTO;
import com.piseth.java.school.phoneshop.phoneshop.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.mapper.ProductMapper;
import com.piseth.java.school.phoneshop.phoneshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")

public class ProductController {

    @Autowired
    private final ProductService productService;
    private final ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product = productService.create(product);
        return ResponseEntity.ok(product);
    }

    @PostMapping("importProduct")
    public ResponseEntity<?> importProduct(@RequestBody ProductImportDTO productImportDTO){
        productService.importProduct(productImportDTO);
        return ResponseEntity.ok().build();
    }

/*

    @GetMapping("{id}") // Read / GET method
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId) {    //@PathVariable if we write this will error integer is not present
        Brand brand = brandService.getById(brandId);                              //@PathVariable("id") the same as GetMapping it'll work
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

        @GetMapping()
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
        }
    @GetMapping
    public ResponseEntity<?> getBrand(@RequestParam Map<String, String> params) {
        Page<Brand> page = brandService.getBrands(params);
        PageDTO pageDTO = new PageDTO(page);
        List<BrandDTO> list = brandService.getBrands(params)
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
                .collect(Collectors.toList());
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
*/

}
