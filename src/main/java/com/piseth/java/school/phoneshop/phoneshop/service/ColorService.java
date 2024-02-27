package com.piseth.java.school.phoneshop.phoneshop.service;

import com.piseth.java.school.phoneshop.phoneshop.entities.Color;

public interface ColorService {
    Color create(Color color);
    Color getById(Long id);
}
