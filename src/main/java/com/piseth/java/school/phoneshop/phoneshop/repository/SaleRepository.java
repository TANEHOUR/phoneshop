package com.piseth.java.school.phoneshop.phoneshop.repository;


import com.piseth.java.school.phoneshop.phoneshop.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}
