package com.kalayciburak.inventoryservice.model.dto.response;

import java.util.List;

public record ModelResponse(Long id, String name, List<CarResponse> cars) {}
