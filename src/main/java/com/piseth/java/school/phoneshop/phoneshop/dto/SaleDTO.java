package com.piseth.java.school.phoneshop.phoneshop.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
public class SaleDTO {
    @NotEmpty
    private List<ProductSoldDTO> products;
    private LocalDate saleDate;

}
