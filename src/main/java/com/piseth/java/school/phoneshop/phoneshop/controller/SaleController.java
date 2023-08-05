package com.piseth.java.school.phoneshop.phoneshop.controller;

import com.piseth.java.school.phoneshop.phoneshop.dto.SaleDTO;
import com.piseth.java.school.phoneshop.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("sales")
public class SaleController {

    private final SaleService saleService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO){
        saleService.sell(saleDTO);
        return ResponseEntity.ok().build();
    }


}
