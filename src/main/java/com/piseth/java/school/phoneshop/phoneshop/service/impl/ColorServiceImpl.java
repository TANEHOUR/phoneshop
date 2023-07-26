package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.entities.Color;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.repository.ColorRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public Color create(Color color) {
        return null;
    }

    @Override
    public Color getById(Long id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Color", id));
    }
}
