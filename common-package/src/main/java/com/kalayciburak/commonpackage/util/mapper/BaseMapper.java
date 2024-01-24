package com.kalayciburak.commonpackage.util.mapper;

import com.kalayciburak.commonpackage.model.entity.ReferenceEntity;

public interface BaseMapper<DTO, ENTITY extends ReferenceEntity> {
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);
}