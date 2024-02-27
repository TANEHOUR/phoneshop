package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.dto.report.ExpenseReportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.entities.ProductImportHistory;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.SaleRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.impl.ReportServiceImpl;
import com.piseth.java.school.phoneshop.phoneshop.spacification.ProductImportHistorySpeci;
import com.piseth.java.school.phoneshop.phoneshop.utils.ReportTestHelp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ReportServiceTest {
    @Mock
    private SaleRepository saleRepository;
    @Mock
    private SaleDetailRepository saleDetailRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductImportHistoryRepository productImportHistoryRepository;
    private ReportService reportService;

    @BeforeEach
    public void setup() {
        reportService = new ReportServiceImpl(saleRepository, saleDetailRepository, productRepository, productImportHistoryRepository);
    }

    @Test
    public void testGetExpenseReportDTO() {
        //given
        List<ProductImportHistory> importHistories = ReportTestHelp.getProductImportHistories();
        List<Product> products = ReportTestHelp.geProducts();

        //when
        when(productImportHistoryRepository.findAll(Mockito.any(ProductImportHistorySpeci.class)))
                .thenReturn(importHistories);
        when(productRepository.findAllById(anySet()))
                .thenReturn(products);
        List<ExpenseReportDTO> expenseReportDTOs = reportService.getExpenseReportDTO(LocalDate.now().minusMonths(4), LocalDate.now());

        //then
        assertEquals(2, expenseReportDTOs.size());
        ExpenseReportDTO expenseReportDTO = expenseReportDTOs.get(0);
        assertEquals(1, expenseReportDTO.getProductId());
        assertEquals("iPhone 14 pro", expenseReportDTO.getProductName());
        assertEquals(25, expenseReportDTO.getTotalUnit());
        assertEquals(28000d, expenseReportDTO.getTotalAmount().doubleValue());

    }
}
