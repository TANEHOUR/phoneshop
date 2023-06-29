package com.piseth.java.school.phoneshop.phoneshop.repository;


import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // new version we can declare or not by our choice

public interface BrandRepository extends JpaRepository<Brand, Integer> {    // Brand is our class brand that we create,
                                                                            // Integer is Id of brand




}
