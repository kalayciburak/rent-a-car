package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.response.CityResponse;
import com.kalayciburak.inventoryservice.model.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends BaseMapper<CityResponse, City> {
}
