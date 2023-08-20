package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.dto.SaleDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Sale;

public interface SaleService {
    void sell(SaleDTO saleDTO);
    Sale getById(Long saleId);
    void cancelSale(Long saleId);
}
