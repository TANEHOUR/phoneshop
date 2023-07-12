package com.piseth.java.school.phoneshop.phoneshop.utils;

import java.util.List;
public class GeneralUtils {

    public static List<Integer> toInteger(List<String> list) {
        return list.stream()
                .map(s -> s.length())
                .toList();
    }

    public static List<Integer> getEvenNumber(List<Integer> list) {
        return list.stream()
                .filter(n -> n % 2 == 0)
                .toList();
    }
}
