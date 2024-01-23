package com.kalayciburak.inventoryservice.model.dto.response;

import java.util.List;

public record LocationResponse(String address, String cityName, List<CarResponse> cars) {}
