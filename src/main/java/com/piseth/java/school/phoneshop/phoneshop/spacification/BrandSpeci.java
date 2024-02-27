package com.piseth.java.school.phoneshop.phoneshop.spacification;

import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Predicates;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class BrandSpeci implements Specification<Brand> {

    private final BrandFilter brandFilter;
    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Brand> brandRoot, CriteriaQuery<?> query, CriteriaBuilder cBuilder) {

        if (brandFilter.getName() != null) {
            Predicate name = cBuilder.like(cBuilder.upper(brandRoot.get("name")), "%" + brandFilter.getName().toUpperCase() + "%");
            predicates.add(name);
/*            Predicate name = brandRoot.get("name").in(brandFilter.getName());
            predicates.add(name);*/
        }
        if (brandFilter.getId() != null) {
            Predicate id = brandRoot.get("id").in(brandFilter.getId());
            predicates.add(id);
        }
        return cBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
