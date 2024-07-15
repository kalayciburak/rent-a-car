package com.kalayciburak.filterservice.util.mapper;

import com.kalayciburak.commonpackage.util.event.inventory.CarCreatedEvent;
import com.kalayciburak.commonpackage.util.event.inventory.CarUpdatedEvent;
import com.kalayciburak.filterservice.model.dto.response.CarResponse;
import com.kalayciburak.filterservice.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarResponse toDto(Car entity);

    Car toEntity(CarCreatedEvent event);

    @Mapping(target = "id", ignore = true)
    void updateEntity(CarUpdatedEvent event, @MappingTarget Car entity);
}
