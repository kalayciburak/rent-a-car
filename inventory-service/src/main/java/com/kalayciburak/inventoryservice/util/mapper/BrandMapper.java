package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.corepackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.response.BrandResponse;
import com.kalayciburak.inventoryservice.model.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ModelMapper.class})
public interface BrandMapper extends BaseMapper<BrandResponse, Brand> {
}
