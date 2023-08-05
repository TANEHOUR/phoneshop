package com.piseth.java.school.phoneshop.phoneshop.controller;


import com.piseth.java.school.phoneshop.phoneshop.dto.PriceDTO;
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

    @PostMapping("{productId}/setSalePrice")
    public ResponseEntity<?> setSalePrice(@PathVariable Long productId, @RequestBody PriceDTO priceDTO){
        productService.setSalePrice(productId, priceDTO.getPrice());
        return ResponseEntity.ok().build();
    }

}
