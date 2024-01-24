package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.response.CarResponse;
import com.kalayciburak.inventoryservice.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface CarMapper extends BaseMapper<CarResponse, Car> {
    @Mapping(target = "fuel", source = "fuelType.label")
    @Mapping(target = "modelName", source = "model.name")
    @Mapping(target = "color", source = "colorType.label")
    @Mapping(target = "address", source = "location.address")
    @Mapping(target = "cityName", source = "location.city.name")
    @Mapping(target = "transmission", source = "transmissionType.label")
    @Mapping(target = "brandName", source = "model.brand.name")
    CarResponse toDto(Car car);
}
