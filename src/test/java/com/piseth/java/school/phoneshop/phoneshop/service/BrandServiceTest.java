package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.repository.BrandRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;    // we suppose this already test

    private BrandService brandService;

    @BeforeEach
    public void setUp() {
        brandService = new BrandServiceImpl(brandRepository);
    }

/*    @Test
    public void testCreate() {
        //given
        Brand brand1 = new Brand();
        brand1.setName("Apple");
        brand1.setId(1);

        //when
        when(brandRepository.save(any(Brand.class))).thenReturn(brand1);
        Brand brandReturn = brandService.create(new Brand());

        //then
        assertEquals(1, brandReturn.getId());
        assertEquals("Apple", brandReturn.getName());
    }*/

    @Test
    public void testCreate() {

        // given
        Brand brand = new Brand();
        brand.setName("Apple");

        // when
        brandService.create(brand);

        // then
        verify(brandRepository, times(1)).save(brand);

    }

    @Test
    public void testGetByIdSuccess() {

        //given
        Brand brand = new Brand();
        brand.setName("Apple");
        brand.setId(1L);

        //when
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        Brand brandServiceById = brandService.getById(1L);

        //then
        assertEquals(1, brandServiceById.getId());
        assertEquals("Apple", brandServiceById.getName());

    }

    @Test
    public void testGetByIdThrow() {

        //given


        //when
        when(brandRepository.findById(2L)).thenReturn(Optional.empty());
        //Brand brandServiceById = brandService.getById(2);
        assertThatThrownBy(() -> brandService.getById(2L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(String.format("%s with id = %d not found", "Brand", 2));

        //then

    }

}
