package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.dto.ProductReportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.entities.SaleDetail;
import com.piseth.java.school.phoneshop.phoneshop.projection.ProductSold;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.SaleRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.ReportService;
import com.piseth.java.school.phoneshop.phoneshop.spacification.SaleDetailFilter;
import com.piseth.java.school.phoneshop.phoneshop.spacification.SaleDetailSpeci;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate, endDate);
    }

    @Override
    public List<ProductReportDTO> getProductReport(LocalDate startDate, LocalDate endDate) {
        List<ProductReportDTO> list = new ArrayList<>();
        SaleDetailFilter saleDetailFilter = new SaleDetailFilter();
        saleDetailFilter.setStartDate(startDate);
        saleDetailFilter.setEndDate(endDate);
        Specification<SaleDetail> spec = new SaleDetailSpeci(saleDetailFilter);
        List<SaleDetail> saleDetails = saleDetailRepository.findAll(spec);

        List<Long> productIds = saleDetails.stream().map(saleDetail -> saleDetail.getProduct().getId()).toList();
        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream().collect(Collectors.groupingBy(SaleDetail::getProduct));
        for (var entry : saleDetailMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getId());
            List<SaleDetail> saleDetailList = entry.getValue();

            // total unit

            Integer unit = saleDetailList.stream().map(SaleDetail::getUnit).reduce(0, (a, b) -> a + b); //0 identity


/*            saleDetailList.stream()
                    .map(saleDetail -> saleDetail.getUnit() * saleDetail.getAmount().doubleValue())
                    .reduce(0.0, (a, b) -> a + b); // or 0d*/
            double totalAmmount = saleDetailList.stream().mapToDouble(saleDetail -> saleDetail.getUnit() * saleDetail.getAmount().doubleValue()).sum();


            ProductReportDTO reportDTO = new ProductReportDTO();
            reportDTO.setProductId(product.getId());
            reportDTO.setProductName(product.getName());
            reportDTO.setUnit(unit);
            reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmmount));
            list.add(reportDTO);
        }
        return list;

    }
}
