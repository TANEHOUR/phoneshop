package com.piseth.java.school.phoneshop.phoneshop.mapper;

import com.piseth.java.school.phoneshop.phoneshop.dto.ModelDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import com.piseth.java.school.phoneshop.phoneshop.entities.Model;
import com.piseth.java.school.phoneshop.phoneshop.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BrandService.class})    // uses because BrandService have Integer
public interface ModelMapper {
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
    @Mapping(target = "brand", source = "brandId")
    Model toModel(ModelDTO modelDTO);

    @Mapping(target = "brandId", source = "model.id")
    ModelDTO toModelDTO(Model model);

/*    default Brand toBrand(Integer brId){
        Brand brand = new Brand();
        brand.setId(brId);
        return brand;
    }*/

}
