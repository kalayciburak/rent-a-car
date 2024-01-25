package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.commonpackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.request.ModelRequest;
import com.kalayciburak.inventoryservice.model.dto.response.ModelResponse;
import com.kalayciburak.inventoryservice.model.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface ModelMapper extends BaseMapper<ModelResponse, Model> {
    Model toEntity(ModelRequest request);

    void updateEntity(ModelRequest request, @MappingTarget Model entity);
}
