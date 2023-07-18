package com.piseth.java.school.phoneshop.phoneshop.controller;

import com.piseth.java.school.phoneshop.phoneshop.dto.ModelDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Model;
import com.piseth.java.school.phoneshop.phoneshop.mapper.ModelEntityMapper;
import com.piseth.java.school.phoneshop.phoneshop.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {

    //    @Autowired
    private final ModelService modelService;
    private final ModelEntityMapper modelEntityMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO) {
        Model model = modelEntityMapper.toModel(modelDTO);
        model = modelService.save(model);
        return ResponseEntity.ok(modelEntityMapper.toModelDTO(model));
    }


}
