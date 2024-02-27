package com.piseth.java.school.phoneshop.phoneshop.spacification;

import lombok.Data;

import java.time.LocalDate;
@Data
public class SaleDetailFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
