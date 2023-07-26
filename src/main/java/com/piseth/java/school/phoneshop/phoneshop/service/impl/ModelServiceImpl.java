package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.entities.Model;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.repository.ModelRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

    //    @Autowired
    private final ModelRepository modelRepository;

    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getByBrand(Long brandId) {
        return modelRepository.findByBrandId(brandId);
    }

    @Override
    public Model getById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model", id));
    }
}
