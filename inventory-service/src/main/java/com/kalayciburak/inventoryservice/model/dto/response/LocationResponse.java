package com.kalayciburak.inventoryservice.model.dto.response;

import java.util.List;

public record LocationResponse(Long id, String address, String cityName, List<CarResponse> cars) {}
