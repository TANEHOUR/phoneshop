package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.dto.ProductReportDTO;
import com.piseth.java.school.phoneshop.phoneshop.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
    List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate);
}
