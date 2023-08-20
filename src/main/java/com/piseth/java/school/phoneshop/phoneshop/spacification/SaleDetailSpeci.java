package com.piseth.java.school.phoneshop.phoneshop.spacification;

import com.piseth.java.school.phoneshop.phoneshop.entities.Sale;
import com.piseth.java.school.phoneshop.phoneshop.entities.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class SaleDetailSpeci implements Specification<SaleDetail> {

    private SaleDetailFilter saleDetailFilter;

    @Override
    public Predicate toPredicate(Root<SaleDetail> saleDetailRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Join<SaleDetail, Sale> sale = saleDetailRoot.join("sale");
        if (Objects.nonNull(saleDetailFilter.getStartDate())) {
//            saleDetailRoot.get("soleDate");
        criteriaBuilder.greaterThanOrEqualTo(sale.get("soldDate"), saleDetailFilter.getStartDate());
        }
        if (Objects.nonNull(saleDetailFilter.getStartDate())) {
            criteriaBuilder.lessThanOrEqualTo(sale.get("soldDate"), saleDetailFilter.getEndDate());
        }
        Predicate predicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        return predicate;
    }
}
