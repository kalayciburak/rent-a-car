package com.kalayciburak.inventoryservice.model.dto.response;

import java.util.List;

public record BrandResponse(Long id, String name, List<ModelResponse> models) {}
