package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.corepackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.response.ModelResponse;
import com.kalayciburak.inventoryservice.model.entity.Model;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CarMapper.class})
public interface ModelMapper extends BaseMapper<ModelResponse, Model> {
}
