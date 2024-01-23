package com.kalayciburak.corepackage.util.mapper;

import com.kalayciburak.corepackage.model.entity.ReferenceEntity;

public interface BaseMapper<DTO, ENTITY extends ReferenceEntity> {
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);
}