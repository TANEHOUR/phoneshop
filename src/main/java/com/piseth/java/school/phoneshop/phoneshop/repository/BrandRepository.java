package com.piseth.java.school.phoneshop.phoneshop.repository;


import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // new version we can declare or not by our choice

/* Brand is our class brand that we create,
 Integer is Id of brand*/
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    //    List<Brand> findByNameIgnoreCase(String name);
    List<Brand> findByNameLike(String name); // jpa query method
    List<Brand> findByNameContaining(String name);

}
