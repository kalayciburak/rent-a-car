package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.LocationRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.LocationResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.LocationWithCarsResponse;
import com.kalayciburak.inventoryservice.model.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface LocationMapper extends BaseMapper<LocationResponse, Location> {
    @Mapping(target = "cityName", source = "city.name")
    LocationResponse toDto(Location location);

    Location toEntity(LocationRequest request);

    @Mapping(target = "cityName", source = "city.name")
    LocationWithCarsResponse toDtoWithCars(Location location);

    void updateEntity(LocationRequest request, @MappingTarget Location entity);
}