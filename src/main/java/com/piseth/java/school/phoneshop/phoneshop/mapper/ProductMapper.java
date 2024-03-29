package com.piseth.java.school.phoneshop.phoneshop.mapper;

import com.piseth.java.school.phoneshop.phoneshop.dto.ProductDTO;
import com.piseth.java.school.phoneshop.phoneshop.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.entities.ProductImportHistory;
import com.piseth.java.school.phoneshop.phoneshop.service.ColorService;
import com.piseth.java.school.phoneshop.phoneshop.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring"
        , uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "dateImport", source = "productImportDTO.importDate")
    @Mapping(target = "pricePerUnit", source = "productImportDTO.importPrice")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "id", ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDTO productImportDTO, Product product);

}
