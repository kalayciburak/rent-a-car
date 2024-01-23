package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.corepackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {
    ResponseItem<BrandResponse> findById(Long id);

    ResponseItem<List<BrandResponse>> findAll();
}
