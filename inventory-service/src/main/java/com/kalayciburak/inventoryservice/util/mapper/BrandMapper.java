package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.BrandRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.BrandResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.BrandWithModelsResponse;
import com.kalayciburak.inventoryservice.model.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ModelMapper.class})
public interface BrandMapper extends BaseMapper<BrandResponse, Brand> {
    Brand toEntity(BrandRequest request);

    BrandWithModelsResponse toDtoWithModels(Brand entity);

    void updateEntity(BrandRequest request, @MappingTarget Brand entity);
}
