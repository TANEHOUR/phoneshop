package com.piseth.java.school.phoneshop.phoneshop.repository;


import com.piseth.java.school.phoneshop.phoneshop.entities.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

}
