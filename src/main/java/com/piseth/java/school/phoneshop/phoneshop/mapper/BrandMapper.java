package com.piseth.java.school.phoneshop.phoneshop.mapper;

import com.piseth.java.school.phoneshop.phoneshop.dto.BrandDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
/*//    use when fields name are not the same, and we need to convert
    // class Brand have field version and class brandDTO have field vs (version)
//    @Mapping(target = "version", source = "vs")*/
    // use INSTANCE to call method by create this field (tip 1 to call mapstruct)
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand toBrand(BrandDTO brandDTO);
    BrandDTO toBrandDTO(Brand entity);

}
