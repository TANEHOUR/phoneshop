package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.dto.ModelDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Model;
import com.piseth.java.school.phoneshop.phoneshop.mapper.ModelMapper;
import com.piseth.java.school.phoneshop.phoneshop.repository.ModelRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.BrandService;
import com.piseth.java.school.phoneshop.phoneshop.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

    //    @Autowired
    private final ModelRepository modelRepository;

    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }
}
