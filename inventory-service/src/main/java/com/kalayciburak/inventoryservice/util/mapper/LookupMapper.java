package com.kalayciburak.inventoryservice.util.mapper;

import com.kalayciburak.corepackage.util.mapper.BaseMapper;
import com.kalayciburak.inventoryservice.model.dto.response.LookupResponse;
import com.kalayciburak.inventoryservice.model.entity.Lookup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LookupMapper extends BaseMapper<LookupResponse, Lookup> {
}
