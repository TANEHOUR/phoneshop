package com.piseth.java.school.phoneshop.phoneshop.repository;


import com.piseth.java.school.phoneshop.phoneshop.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long> {


}
