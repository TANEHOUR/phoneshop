package com.piseth.java.school.phoneshop.phoneshop.spacification;

import com.piseth.java.school.phoneshop.phoneshop.entities.ProductImportHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ProductImportHistorySpeci implements Specification<ProductImportHistory> {

    private ProductImportHistoryFilter productImportHistoryFilter;

    @Override
    public Predicate toPredicate(Root<ProductImportHistory> productImportHistoryRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        //Join<SaleDetail, Sale> sale = saleDetailRoot.join("sale");
        if (Objects.nonNull(productImportHistoryFilter.getStartDate())) {
//            saleDetailRoot.get("soleDate");
            criteriaBuilder.greaterThanOrEqualTo(productImportHistoryRoot.get("dateImport"), productImportHistoryFilter.getStartDate());
        }
        if (Objects.nonNull(productImportHistoryFilter.getStartDate())) {
            criteriaBuilder.lessThanOrEqualTo(productImportHistoryRoot.get("dateImport"), productImportHistoryFilter.getEndDate());
        }
        Predicate predicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        return predicate;
    }
}
