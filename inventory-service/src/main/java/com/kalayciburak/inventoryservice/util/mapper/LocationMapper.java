package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.corepackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.response.LocationResponse;
import com.kalayciburak.inventoryservice.model.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper extends BaseMapper<LocationResponse, Location> {
    @Mapping(target = "cityName", source = "city.name")
    LocationResponse toDto(Location location);
}