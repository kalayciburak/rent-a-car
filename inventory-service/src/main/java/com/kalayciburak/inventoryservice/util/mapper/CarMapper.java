package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.event.inventory.CarCreatedEvent;
import com.kalayciburak.commonpackage.util.event.inventory.CarUpdatedEvent;
import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.CarRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.CarResponse;
import com.kalayciburak.inventoryservice.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface CarMapper extends BaseMapper<CarResponse, Car> {
    @Mapping(target = "fuelType.id", source = "fuelId")
    @Mapping(target = "model.id", source = "modelId")
    @Mapping(target = "model.brand.id", source = "brandId")
    @Mapping(target = "colorType.id", source = "colorId")
    @Mapping(target = "location.id", source = "locationId")
    @Mapping(target = "transmissionType.id", source = "transmissionId")
    @Mapping(target = "fuelType.label", source = "fuel")
    @Mapping(target = "model.name", source = "modelName")
    @Mapping(target = "colorType.label", source = "color")
    @Mapping(target = "location.address", source = "address")
    @Mapping(target = "carStatus.label", source = "carStatus")
    @Mapping(target = "model.brand.name", source = "brandName")
    @Mapping(target = "location.city.name", source = "cityName")
    @Mapping(target = "transmissionType.label", source = "transmission")
    Car toEntity(CarResponse carResponse);

    @Mapping(target = "brandId", source = "model.brand.id")
    @Mapping(target = "modelId", source = "model.id")
    @Mapping(target = "cityId", source = "location.city.id")
    @Mapping(target = "locationId", source = "location.id")
    @Mapping(target = "carStatusId", source = "carStatus.id")
    @Mapping(target = "fuelId", source = "fuelType.id")
    @Mapping(target = "transmissionId", source = "transmissionType.id")
    @Mapping(target = "colorId", source = "colorType.id")
    @Mapping(target = "fuel", source = "fuelType.label")
    @Mapping(target = "modelName", source = "model.name")
    @Mapping(target = "color", source = "colorType.label")
    @Mapping(target = "address", source = "location.address")
    @Mapping(target = "carStatus", source = "carStatus.label")
    @Mapping(target = "brandName", source = "model.brand.name")
    @Mapping(target = "cityName", source = "location.city.name")
    @Mapping(target = "transmission", source = "transmissionType.label")
    CarResponse toDto(Car car);

    @Mapping(target = "fuelType.id", source = "fuelTypeId")
    @Mapping(target = "model.id", source = "modelId")
    @Mapping(target = "colorType.id", source = "colorTypeId")
    @Mapping(target = "location.id", source = "locationId")
    @Mapping(target = "transmissionType.id", source = "transmissionTypeId")
    Car toEntity(CarRequest request);

    @Mapping(target = "fuelType.id", source = "fuelTypeId")
    @Mapping(target = "model.id", source = "modelId")
    @Mapping(target = "colorType.id", source = "colorTypeId")
    @Mapping(target = "location.id", source = "locationId")
    @Mapping(target = "transmissionType.id", source = "transmissionTypeId")
    void updateEntity(CarRequest request, @MappingTarget Car entity);

    @Mapping(target = "carId", source = "id")
    CarCreatedEvent toCreatedEvent(CarResponse response);

    @Mapping(target = "carId", source = "id")
    CarUpdatedEvent toUpdatedEvent(CarResponse response);
}
