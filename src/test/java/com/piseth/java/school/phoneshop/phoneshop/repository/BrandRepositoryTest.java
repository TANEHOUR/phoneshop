package com.piseth.java.school.phoneshop.phoneshop.repository;

import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BrandRepositoryTest {
    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testFindByNameLike() {
        //given
        Brand brand = new Brand();
        brand.setName("Apple");
        Brand brand2 = new Brand();
        brand2.setName("Samsung");

        brandRepository.save(brand);
        brandRepository.save(brand2);

        //when
        List<Brand> brands = brandRepository.findByNameLike("%A%");

        //then
        assertEquals(1, brands.size());
        assertEquals("Apple", brands.get(0).getName());
        assertEquals(1, brands.get(0).getId());

    }



}
