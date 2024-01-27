package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ModelRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.ModelResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.ModelWithCarsResponse;
import com.kalayciburak.inventoryservice.model.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface ModelMapper extends BaseMapper<ModelResponse, Model> {
    Model toEntity(ModelRequest request);

    ModelWithCarsResponse toDtoWithCars(Model entity);

    void updateEntity(ModelRequest request, @MappingTarget Model entity);
}
