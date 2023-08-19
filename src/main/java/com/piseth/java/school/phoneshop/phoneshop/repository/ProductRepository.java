package com.piseth.java.school.phoneshop.phoneshop.repository;


import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);

}
