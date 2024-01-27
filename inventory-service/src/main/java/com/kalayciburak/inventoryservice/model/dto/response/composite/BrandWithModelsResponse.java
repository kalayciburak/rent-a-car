package com.kalayciburak.inventoryservice.model.dto.response.composite;

import com.kalayciburak.inventoryservice.model.dto.response.basic.ModelResponse;

import java.util.List;

public record BrandWithModelsResponse(Long id, String name, List<ModelResponse> models) {}
