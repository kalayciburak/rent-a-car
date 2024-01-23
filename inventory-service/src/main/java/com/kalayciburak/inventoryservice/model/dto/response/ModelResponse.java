package com.kalayciburak.inventoryservice.model.dto.response;

import java.util.List;

public record ModelResponse(String name, List<CarResponse> cars) {}
