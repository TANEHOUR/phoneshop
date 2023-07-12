package com.piseth.java.school.phoneshop.phoneshop.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GeneralUtilsTest {

    @Test
    public void testToInteger(){
        //Given
        List<String> names = List.of("Dara", "Seyha", "Vathana");

        //When
        List<Integer> list = GeneralUtils.toInteger(names);

        //Then
        assertEquals(3, list.size());
        assertEquals(4, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(7, list.get(2));
    }
    @Test
    public void testGetEvenNumber(){
        //given
        List<Integer> list = List.of(5, 2, 8, 3, 10, 11);

        //when
        List<Integer> evenNumber = GeneralUtils.getEvenNumber(list);

        //then
        assertEquals(3, evenNumber.size());
        assertEquals(2, list.get(1));

    }

}
