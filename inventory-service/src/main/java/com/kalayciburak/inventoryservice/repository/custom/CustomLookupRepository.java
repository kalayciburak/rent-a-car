package com.kalayciburak.inventoryservice.repository.custom;

import com.kalayciburak.inventoryservice.model.entity.Lookup;

import java.util.List;

public interface CustomLookupRepository {
    List<Lookup> findChildrenByParentId(Long parentId);

    List<Lookup> findChildrenByParentKey(String key);

    List<Lookup> findChildrenByParentLabel(String label);
}